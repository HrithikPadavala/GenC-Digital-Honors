package com.cognizant.controller.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.cognizant.model.PensionerDetail;
import com.cognizant.repository.PensionerDetailRepository;

@SpringBootTest
public class PensionerDetailsControllerTests {
	@Autowired
	private PensionerDetailRepository pensionerDetailRepository;
  @Test
  void findByAadhaarNumber() {
	  String aadhaarNumber = "123456789012";
	  PensionerDetail pensionerDetail = pensionerDetailRepository.findById(aadhaarNumber).get();
	  assertNotNull(pensionerDetail);
  }
}
