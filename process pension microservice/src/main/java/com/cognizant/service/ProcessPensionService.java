package com.cognizant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.model.PensionDetail;
import com.cognizant.model.PensionerDetail;
import com.cognizant.model.PensionerInput;
import com.cognizant.repository.PensionDetailRepository;

@Service
public class ProcessPensionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessPensionService.class);
	private PensionDetailRepository pensionDetailRepository;
	@Autowired
	private PensionDetail pensionDetail;

	@Autowired
	public ProcessPensionService(PensionDetailRepository pensionDetailRepository) {
		this.pensionDetailRepository = pensionDetailRepository;
	}

	
	//calculating pension detail and saving to database
	
	public PensionDetail getPensionDetail(PensionerDetail pensionerDetail, PensionerInput pensionerInput) {
		LOGGER.info("STARTED - getPensionDetail");
		if(pensionerDetail.getPensionType().equalsIgnoreCase("self")) {
			  pensionDetail.setPensionAmount(0.8*pensionerDetail.getSalary()+pensionerDetail.getAllowance());
		  }
		  else {
			  pensionDetail.setPensionAmount(0.5*pensionerDetail.getSalary()+pensionerDetail.getAllowance());
		  }
		  if(pensionerDetail.getBankDetail().getBankType().equalsIgnoreCase("public")) {
			  pensionDetail.setBankServiceCharge(500);
		  }
		  else {
			  pensionDetail.setBankServiceCharge(550);
		  }
		  pensionDetail.setName(pensionerDetail.getName());
		  pensionDetail.setDateOfBirth(pensionerDetail.getDateOfBirth());
		  pensionDetail.setPanNumber(pensionerDetail.getPanNumber());
		  pensionDetail.setPensiontype(pensionerDetail.getPensionType());
		LOGGER.info("END - getPensionDetail");
		return pensionDetail;

	}

	public PensionDetail savePensionDetail(PensionDetail pensionDetail) {
		LOGGER.info("STARTED - savePensionDetail");
		LOGGER.info("END - savePensionDetail");
		return pensionDetailRepository.save(pensionDetail);

	}

}
