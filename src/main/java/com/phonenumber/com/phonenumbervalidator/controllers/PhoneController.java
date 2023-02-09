package com.phonenumber.com.phonenumbervalidator.controllers;

import com.phonenumber.com.phonenumbervalidator.dtos.PhoneNumberDto;
import com.phonenumber.com.phonenumbervalidator.services.CustomerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1")
public class PhoneController {

    private final CustomerService customerService;

    public PhoneController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/phones")
    public List<PhoneNumberDto> getAllPhoneNumbers() {
        return customerService.getAllPhoneNumbers();
    }
}
