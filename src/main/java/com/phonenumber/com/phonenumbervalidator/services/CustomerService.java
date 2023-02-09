package com.phonenumber.com.phonenumbervalidator.services;

import com.phonenumber.com.phonenumbervalidator.dtos.PhoneNumberDto;
import com.phonenumber.com.phonenumbervalidator.entities.Customer;
import com.phonenumber.com.phonenumbervalidator.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.phonenumber.com.phonenumbervalidator.utils.Constants.*;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<PhoneNumberDto> getAllPhoneNumbers() {
        List<Customer> phoneNumbers = customerRepository.findAll();
        return phoneNumbers.stream()
                .map(this::mapPhoneNumberToDto)
                .collect(Collectors.toList());
    }

    private PhoneNumberDto mapPhoneNumberToDto(Customer phoneNumber) {
        PhoneNumberDto dto = new PhoneNumberDto();
        dto.setName(phoneNumber.getName());
        dto.setPhone(phoneNumber.getPhone());

        String countryCode = extractCountryCode(phoneNumber.getPhone());
        dto.setCountryCode(countryCode);

        String country = getCountryByCountryCode(countryCode);
        dto.setCountry(country);

        String state = validatePhoneNumber(phoneNumber.getPhone(), countryCode);
        dto.setState(state);

        return dto;
    }

    public String extractCountryCode(String phone) {
        // Extract country code from the phone number
        String countryCode = "";
        Pattern pattern = Pattern.compile("^\\(\\d{3}\\)");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.find()) {
            countryCode = matcher.group();
            countryCode = "+" + countryCode.substring(1, countryCode.length() - 1);
        }
        return countryCode;
    }

    public String getCountryByCountryCode(String countryCode) {
        // Map country code to country name
        Map<String, String> countryCodeMap = new HashMap<>();
        countryCodeMap.put(CAMEROON_CODE, "Cameroon");
        countryCodeMap.put(ETHIOPIA_CODE, "Ethiopia");
        countryCodeMap.put(MOROCCO_CODE, "Morocco");
        countryCodeMap.put(MOZAMBIQUE_CODE, "Mozambique");
        countryCodeMap.put(UGANDA_CODE, "Uganda");

        return countryCodeMap.getOrDefault(countryCode, "Unknown");
    }

    public String validatePhoneNumber(String phone, String countryCode) {
        String pattern = "";
        if (countryCode.equals("+237")) {
            pattern = CAMEROON_REGEX;
        } else if (countryCode.equals("+251")) {
            pattern = ETHIOPIA_REGEX;
        } else if (countryCode.equals("+212")) {
            pattern = MOROCCO_REGEX;
        } else if (countryCode.equals("+258")) {
            pattern = MOZAMBIQUE_REGEX;
        } else if (countryCode.equals("+256")) {
            pattern = UGANDA_REGEX;
        }
        if (!pattern.isEmpty()) {
            Pattern phonePattern = Pattern.compile(pattern);
            Matcher matcher = phonePattern.matcher(phone);
            if (matcher.matches()) {
                return "valid";
            }
        }
        return "not valid";
    }
}
