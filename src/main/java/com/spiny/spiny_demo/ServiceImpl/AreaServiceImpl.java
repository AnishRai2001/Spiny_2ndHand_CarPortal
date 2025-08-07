//package com.spiny.spiny_demo.ServiceImpl;
//
//import com.spiny.spiny_demo.Dto.AreaDto;
//import com.spiny.spiny_demo.Repository.AgentRepository;
//import com.spiny.spiny_demo.Repository.AreaRepository;
//import com.spiny.spiny_demo.Service.Areaservice;
//import com.spiny.spiny_demo.entity.Agent;
//import com.spiny.spiny_demo.entity.Area;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AreaServiceImpl implements Areaservice {
//
//    @Autowired
//    private AreaRepository areaRepository;
//
//    @Autowired
//    private AgentRepository agentRepository;
//
//    @Override
//    public Area findByPincode(int pincode) {
//        return areaRepository.findByPincode(pincode)
//                .orElseThrow(() -> new RuntimeException("Area not found with pincode: " + pincode));
//    }
//
//    @Override
//    public Area saveArea(AreaDto dto) {
//        Agent agent = agentRepository.findById(dto.getAgentId())
//                .orElseThrow(() -> new RuntimeException("Agent not found with ID: " + dto.getAgentId()));
//
//        Area area = new Area();
//        area.setPincode(dto.getPincode());
//        area.setAgent(agent);
//
//        return areaRepository.save(area);
//    }
//
//}
