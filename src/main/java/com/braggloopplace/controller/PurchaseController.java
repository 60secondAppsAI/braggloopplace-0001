package com.braggloopplace.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.braggloopplace.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.braggloopplace.domain.Purchase;
import com.braggloopplace.dto.PurchaseDTO;
import com.braggloopplace.dto.PurchaseSearchDTO;
import com.braggloopplace.dto.PurchasePageDTO;
import com.braggloopplace.service.PurchaseService;
import com.braggloopplace.dto.common.RequestDTO;
import com.braggloopplace.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/purchase")
@RestController
public class PurchaseController {

	private final static Logger logger = LoggerFactory.getLogger(PurchaseController.class);

	@Autowired
	PurchaseService purchaseService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Purchase> getAll() {

		List<Purchase> purchases = purchaseService.findAll();
		
		return purchases;	
	}

	//@ReadAccess
	@GetMapping(value = "/{purchaseId}")
	@ResponseBody
	public PurchaseDTO getPurchase(@PathVariable Integer purchaseId) {
		
		return (purchaseService.getPurchaseDTOById(purchaseId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addPurchase", method = RequestMethod.POST)
	public ResponseEntity<?> addPurchase(@RequestBody PurchaseDTO purchaseDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = purchaseService.addPurchase(purchaseDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/purchases")
	public ResponseEntity<PurchasePageDTO> getPurchases(PurchaseSearchDTO purchaseSearchDTO) {
 
		return purchaseService.getPurchases(purchaseSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updatePurchase", method = RequestMethod.POST)
	public ResponseEntity<?> updatePurchase(@RequestBody PurchaseDTO purchaseDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = purchaseService.updatePurchase(purchaseDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
