package com.spiny.spiny_demo.Dto;

import lombok.Data;

import java.util.List;

@Data
public class AgentDTO {
    private int id;
    private String name;
    private String mobile;
    private String email;
    private List<AreaDto> areas;
}
