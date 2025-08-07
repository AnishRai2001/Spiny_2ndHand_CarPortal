package com.spiny.spiny_demo.Service;

import com.spiny.spiny_demo.entity.Agent;

import java.util.Optional;

public interface AgentService {
    public Agent createAgent(Agent agent);
    public Optional<Agent> getAgentById(Integer id);
}
