package com.spiny.spiny_demo.Controller;

import com.spiny.spiny_demo.Dto.AreaDto;
import com.spiny.spiny_demo.Dto.CustomerDTO;
import com.spiny.spiny_demo.Repository.AgentRepository;
import com.spiny.spiny_demo.Repository.AreaRepository;
import com.spiny.spiny_demo.Repository.CustomerRepository;
import com.spiny.spiny_demo.Service.SmsService;
import com.spiny.spiny_demo.Structure.ResponseStructure;
import com.spiny.spiny_demo.entity.Agent;
import com.spiny.spiny_demo.entity.Area;
import com.spiny.spiny_demo.entity.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crm")
public class CrmController {

    @Autowired
    private SmsService smsService;

    private final AgentRepository agentRepo;
    private final AreaRepository areaRepo;
    private final CustomerRepository customerRepo;

    public CrmController(AgentRepository agentRepo, AreaRepository areaRepo, CustomerRepository customerRepo) {
        this.agentRepo = agentRepo;
        this.areaRepo = areaRepo;
        this.customerRepo = customerRepo;
    }

    @PostMapping("/agents")
    public Agent createAgent(@RequestBody Agent agent) {
        return agentRepo.save(agent);
    }

    @GetMapping("/agents")
    public List<Agent> getAllAgents() {
        return agentRepo.findAll();
    }

    @GetMapping("/agents/{id}")
    public Agent getAgentById(@PathVariable int id) {
        return agentRepo.findById(id).orElse(null);
    }

    @PostMapping("/areas")
    public Area createArea(@RequestBody AreaDto areaDTO) {
        Agent agent = agentRepo.findById(areaDTO.getAgentId()).orElse(null);
        if (agent == null) return null;

        Area area = new Area();
        area.setPincode(areaDTO.getPincode());
        area.setAgent(agent);

        return areaRepo.save(area);
    }

    @GetMapping("/areas")
    public List<Area> getAllAreas() {
        return areaRepo.findAll();
    }

    @PostMapping("/customers")
    public ResponseEntity<ResponseStructure<Customer>> createCustomer(@RequestBody CustomerDTO customerDTO) {
        List<Area> areaList = areaRepo.findByPincode(customerDTO.getPincode());

        if (areaList.isEmpty()) {
            ResponseStructure<Customer> response = new ResponseStructure<>();
            response.setSuccess(false);
            response.setMessage("No area found for the given pincode");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Area area = areaList.get(0); // Adjust if you want a different area selection logic
        Agent agent = area.getAgent();

        if (agent == null) {
            ResponseStructure<Customer> response = new ResponseStructure<>();
            response.setSuccess(false);
            response.setMessage("No agent assigned to this area");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        customer.setAssignedAgent(agent);

        Customer savedCustomer = customerRepo.save(customer); // Save the customer here

        // Prepare the message for agent and customer
        String agentNumber = formatPhoneNumber(agent.getMobile());
        String customerNumber = formatPhoneNumber(customer.getMobile()); // use customer's number, not 'visit'

        String agentMsg = "You have been assigned to Customer: " + savedCustomer.getId();
        String customerMsg = "Agent " + agent.getName() + " (ID: " + agent.getId() + ") has been assigned to your visit.";

        // Send SMS
        smsService.sendSms(agentNumber, agentMsg);
        smsService.sendSms(customerNumber, customerMsg);

        // Create the response structure
        ResponseStructure<Customer> response = new ResponseStructure<>();
        response.setSuccess(true);
        response.setMessage("Customer created and assigned to agent: " + agent.getName());
        response.setData(savedCustomer);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/allocate-agent")
    public ResponseEntity<ResponseStructure<String>> allocateAgentToVisit(@RequestParam int customerVisitId, @RequestParam int agentId) {
        // Check if agent exists
        Agent agent = agentRepo.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        // Check if visit exists (here visit is treated as customer)
        Customer customer = customerRepo.findById(customerVisitId)
                .orElseThrow(() -> new RuntimeException("Customer visit not found"));

        // Assign agent to the customer (assuming customer visit is the same entity)
        customer.setAssignedAgent(agent);
        customerRepo.save(customer);

        ResponseStructure<String> response = new ResponseStructure<>();
        response.setSuccess(true);
        response.setMessage("Agent allocated successfully to the customer visit.");
        response.setData("Agent allocation completed.");

        return ResponseEntity.ok(response);
    }

    // Helper method for formatting phone number
    private String formatPhoneNumber(String phoneNumber) {
        // Ensure the phone number is in the correct format (E.164)
        return phoneNumber != null && phoneNumber.startsWith("+") ? phoneNumber : "+" + phoneNumber;
    }
}
