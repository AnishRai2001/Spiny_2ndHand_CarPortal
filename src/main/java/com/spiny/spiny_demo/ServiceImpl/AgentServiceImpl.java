package com.spiny.spiny_demo.ServiceImpl;


import com.spiny.spiny_demo.Repository.AgentRepository;
import com.spiny.spiny_demo.Service.AgentService;
import com.spiny.spiny_demo.entity.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Override
    public Agent createAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public Optional<Agent> getAgentById(Integer id) {
        return agentRepository.findById(id);
    }
}
