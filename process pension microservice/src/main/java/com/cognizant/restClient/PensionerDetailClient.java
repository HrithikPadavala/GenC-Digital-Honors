package com.cognizant.restClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.model.PensionerDetail;

@FeignClient(name = "PensionerDetailService", url = "http://localhost:9082")
public interface PensionerDetailClient {
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pensionerDetail/{aadhaarNumber}")
	public PensionerDetail findByAadhaarNumber(@RequestHeader("Authorization") String token1,  @PathVariable String aadhaarNumber);

	@GetMapping("/allDetails")
	public List<PensionerDetail> getAllDetail();
	
}
