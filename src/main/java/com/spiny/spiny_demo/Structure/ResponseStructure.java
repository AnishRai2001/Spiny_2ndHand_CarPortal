package com.spiny.spiny_demo.Structure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStructure<T> {

    private Boolean success;
    private String message;
    private T data;

}
