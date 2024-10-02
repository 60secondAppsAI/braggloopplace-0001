package com.braggloopplace.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggloopplace.domain.Purchase;
import com.braggloopplace.dto.PurchaseDTO;
import com.braggloopplace.dto.PurchaseSearchDTO;
import com.braggloopplace.dto.PurchasePageDTO;
import com.braggloopplace.dto.PurchaseConvertCriteriaDTO;
import com.braggloopplace.service.GenericService;
import com.braggloopplace.dto.common.RequestDTO;
import com.braggloopplace.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PurchaseService extends GenericService<Purchase, Integer> {

	List<Purchase> findAll();

	ResultDTO addPurchase(PurchaseDTO purchaseDTO, RequestDTO requestDTO);

	ResultDTO updatePurchase(PurchaseDTO purchaseDTO, RequestDTO requestDTO);

    Page<Purchase> getAllPurchases(Pageable pageable);

    Page<Purchase> getAllPurchases(Specification<Purchase> spec, Pageable pageable);

	ResponseEntity<PurchasePageDTO> getPurchases(PurchaseSearchDTO purchaseSearchDTO);
	
	List<PurchaseDTO> convertPurchasesToPurchaseDTOs(List<Purchase> purchases, PurchaseConvertCriteriaDTO convertCriteria);

	PurchaseDTO getPurchaseDTOById(Integer purchaseId);







}





