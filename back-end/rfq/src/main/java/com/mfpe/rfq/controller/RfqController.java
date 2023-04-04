package com.mfpe.rfq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.rfq.exception.PartNotFoundException;

import com.mfpe.rfq.model.RfqDetail;
import com.mfpe.rfq.model.Supplier;
import com.mfpe.rfq.service.RfqService;

import lombok.extern.slf4j.Slf4j;


@CrossOrigin
@Slf4j
@RestController

public class RfqController {
	@Autowired
	RfqService rfqService;

	@GetMapping("/getrfqofplant/{partId}")
	public ResponseEntity<RfqDetail> getRfqOfPlant(@PathVariable int partId, @RequestHeader("Authorization") final String token) throws Exception {
		log.info("Inside GetRfqDeatilOfPlant of RfqController");
		RfqDetail rfq = rfqService.getRfqOfPlant(partId,token);
		return new ResponseEntity<>(rfq, HttpStatus.OK);
	}

	@GetMapping("/getpotentialvendorsofrfq/{rfqId}")
	public ResponseEntity<List<Supplier>> getPotentialVendorsOfRfq(@PathVariable int rfqId,@RequestHeader("Authorization") final String token) throws Exception {
		log.info("Inside PotentailVendorsOfRfq of RfqController");
		List<Supplier> supplierList = rfqService.getPotentialVendorsOfRfq(rfqId,token);
		if (supplierList == null || supplierList.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(supplierList, HttpStatus.OK);
	}

	/*
	 * @GetMapping("/getrfqdetails/{rfqId}") public ResponseEntity<List<RfqDetail>>
	 * getRfqDetail(@PathVariable int rfqId) { List<RfqDetail> rfqDetailList =
	 * rfqService.getRfqDetails(rfqId); if(rfqDetailList == null ||
	 * rfqDetailList.isEmpty()) { return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 * } return new ResponseEntity<>(rfqDetailList, HttpStatus.OK); }
	 */

}
