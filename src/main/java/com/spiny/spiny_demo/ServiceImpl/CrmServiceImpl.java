//// src/main/java/com/spiny/spiny_demo/ServiceImpl/CrmServiceImpl.java
//package com.spiny.spiny_demo.ServiceImpl;
//
//
//import com.spiny.spiny_demo.Dto.CustomerDTO;
//import com.spiny.spiny_demo.Repository.AreaRepository;
//import com.spiny.spiny_demo.Repository.CustomerRepository;
//
//
//import com.spiny.spiny_demo.Service.CrmService;
//import com.spiny.spiny_demo.entity.Agent;
//import com.spiny.spiny_demo.entity.Area;
//import com.spiny.spiny_demo.entity.Customer;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CrmServiceImpl implements CrmService {
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Autowired
//    private AreaRepository areaRepository;
//
//    @Override
//    public Customer createVisit(CustomerDTO dto) {
//        Area area = areaRepository.findByPincode(dto.getPincode())
//                .orElseThrow(() -> new RuntimeException("No agent assigned to pincode: " + dto.getPincode()));
//
//        Agent agent = area.getAgent();
//
//        Customer customer = new Customer();
//        BeanUtils.copyProperties(dto, customer);
//        customer.setAssignedAgent(agent);
//
//
//        return customerRepository.save(customer); // ✅ returns saved entity
//    }
//
//
//}
