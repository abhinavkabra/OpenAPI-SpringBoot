package com.openapi.demo.OpenAPI.controller;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Person {
    
    @NotNull
    private String fName;

    @NotNull
    private String lName;
}