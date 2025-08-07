package com.spiny.spiny_demo.Repository;

import com.spiny.spiny_demo.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Integer> {
}
