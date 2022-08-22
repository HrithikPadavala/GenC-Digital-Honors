package com.cognizant.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.exception.ProcessPensionException;
import com.cognizant.model.PensionDetail;
import com.cognizant.model.PensionerDetail;
import com.cognizant.model.PensionerInput;
import com.cognizant.restClient.PensionerDetailClient;
import com.cognizant.service.ProcessPensionService;

@RestController
public class ProcessPensionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessPensionController.class);
	
	@Autowired
	private PensionDetail pensionDetail;

	@Autowired
	private PensionerDetailClient pensionerDetailClient;
	
	@Autowired
	private ProcessPensionService processPensionService;
	@Autowired


	//getting all details from pensioner details micro service
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/details")
	public List<PensionerDetail> allDetail() {
		LOGGER.info("STARTED - allDetail");
		List<PensionerDetail> pensionerDetail = pensionerDetailClient.getAllDetail();
		LOGGER.info("END - allDetail");
		return pensionerDetail;
	}

	//generating pension detail with pension amount for given user input
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/pensionerInput")
	public PensionDetail getPensionDetail(@RequestHeader(name = "Authorization") String token,
			@RequestBody PensionerInput pensionerInput) {
		LOGGER.info("STARTED - getPensionDetail");
		
		try {
			LOGGER.info("try block - getPensionDetail");
			pensionDetail = processPensionService.getPensionDetail(
					pensionerDetailClient.findByAadhaarNumber(token, pensionerInput.getAadhaarNumber()),
					pensionerInput);

		} catch (Exception e) {
			LOGGER.error("EXCEPTION - getPensionDetail");
			throw new ProcessPensionException("Pensioner Detail not coreect");
		}
		LOGGER.info("END - getPensionDetail");
		processPensionService.savePensionDetail(pensionDetail);
		return pensionDetail;

	}


}
