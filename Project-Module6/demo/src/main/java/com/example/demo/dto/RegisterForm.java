package com.example.demo.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterForm extends LoginForm{
    private String role;
}
