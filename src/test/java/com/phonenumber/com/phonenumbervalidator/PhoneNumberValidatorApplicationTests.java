package com.phonenumber.com.phonenumbervalidator;

import com.phonenumber.com.phonenumbervalidator.repositories.CustomerRepository;
import com.phonenumber.com.phonenumbervalidator.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
class PhoneNumberValidatorApplicationTests {
	private final CustomerRepository customerRepository = mock(CustomerRepository.class);
	private final CustomerService customerService = new CustomerService(customerRepository);
	@Test
	void contextLoads() {
	}


	@Test
	public void testExtractCountryCode() {
		String phoneNumber = "(237) 676456789";
		String expectedCountryCode = "+237";

		String actualCountryCode = customerService.extractCountryCode(phoneNumber);

		assertEquals(expectedCountryCode, actualCountryCode);
	}

	@Test
	public void testGetCountryByCountryCode() {
		String countryCode = "+237";
		String expectedCountry = "Cameroon";

		String actualCountry = customerService.getCountryByCountryCode(countryCode);

		assertEquals(expectedCountry, actualCountry);
	}

	@Test
	public void testValidatePhoneNumber() {
		String phoneNumber = "(237) 676456789";
		String countryCode = "+237";
		String expectedState = "valid";

		String actualState = customerService.validatePhoneNumber(phoneNumber, countryCode);

		assertEquals(expectedState, actualState);
	}

}
