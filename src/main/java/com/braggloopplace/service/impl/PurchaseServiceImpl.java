package com.braggloopplace.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.braggloopplace.dao.GenericDAO;
import com.braggloopplace.service.GenericService;
import com.braggloopplace.service.impl.GenericServiceImpl;
import com.braggloopplace.dao.PurchaseDAO;
import com.braggloopplace.domain.Purchase;
import com.braggloopplace.dto.PurchaseDTO;
import com.braggloopplace.dto.PurchaseSearchDTO;
import com.braggloopplace.dto.PurchasePageDTO;
import com.braggloopplace.dto.PurchaseConvertCriteriaDTO;
import com.braggloopplace.dto.common.RequestDTO;
import com.braggloopplace.dto.common.ResultDTO;
import com.braggloopplace.service.PurchaseService;
import com.braggloopplace.util.ControllerUtils;





@Service
public class PurchaseServiceImpl extends GenericServiceImpl<Purchase, Integer> implements PurchaseService {

    private final static Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);

	@Autowired
	PurchaseDAO purchaseDao;

	


	@Override
	public GenericDAO<Purchase, Integer> getDAO() {
		return (GenericDAO<Purchase, Integer>) purchaseDao;
	}
	
	public List<Purchase> findAll () {
		List<Purchase> purchases = purchaseDao.findAll();
		
		return purchases;	
		
	}

	public ResultDTO addPurchase(PurchaseDTO purchaseDTO, RequestDTO requestDTO) {

		Purchase purchase = new Purchase();

		purchase.setPurchaseId(purchaseDTO.getPurchaseId());


		purchase.setPurchaseDate(purchaseDTO.getPurchaseDate());


		purchase.setAmount(purchaseDTO.getAmount());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		purchase = purchaseDao.save(purchase);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Purchase> getAllPurchases(Pageable pageable) {
		return purchaseDao.findAll(pageable);
	}

	public Page<Purchase> getAllPurchases(Specification<Purchase> spec, Pageable pageable) {
		return purchaseDao.findAll(spec, pageable);
	}

	public ResponseEntity<PurchasePageDTO> getPurchases(PurchaseSearchDTO purchaseSearchDTO) {
	
			Integer purchaseId = purchaseSearchDTO.getPurchaseId(); 
    			String sortBy = purchaseSearchDTO.getSortBy();
			String sortOrder = purchaseSearchDTO.getSortOrder();
			String searchQuery = purchaseSearchDTO.getSearchQuery();
			Integer page = purchaseSearchDTO.getPage();
			Integer size = purchaseSearchDTO.getSize();

	        Specification<Purchase> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, purchaseId, "purchaseId"); 
			
 			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Purchase> purchases = this.getAllPurchases(spec, pageable);
		
		//System.out.println(String.valueOf(purchases.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(purchases.getTotalPages()));
		
		List<Purchase> purchasesList = purchases.getContent();
		
		PurchaseConvertCriteriaDTO convertCriteria = new PurchaseConvertCriteriaDTO();
		List<PurchaseDTO> purchaseDTOs = this.convertPurchasesToPurchaseDTOs(purchasesList,convertCriteria);
		
		PurchasePageDTO purchasePageDTO = new PurchasePageDTO();
		purchasePageDTO.setPurchases(purchaseDTOs);
		purchasePageDTO.setTotalElements(purchases.getTotalElements());
		return ResponseEntity.ok(purchasePageDTO);
	}

	public List<PurchaseDTO> convertPurchasesToPurchaseDTOs(List<Purchase> purchases, PurchaseConvertCriteriaDTO convertCriteria) {
		
		List<PurchaseDTO> purchaseDTOs = new ArrayList<PurchaseDTO>();
		
		for (Purchase purchase : purchases) {
			purchaseDTOs.add(convertPurchaseToPurchaseDTO(purchase,convertCriteria));
		}
		
		return purchaseDTOs;

	}
	
	public PurchaseDTO convertPurchaseToPurchaseDTO(Purchase purchase, PurchaseConvertCriteriaDTO convertCriteria) {
		
		PurchaseDTO purchaseDTO = new PurchaseDTO();
		
		purchaseDTO.setPurchaseId(purchase.getPurchaseId());

	
		purchaseDTO.setPurchaseDate(purchase.getPurchaseDate());

	
		purchaseDTO.setAmount(purchase.getAmount());

	

		
		return purchaseDTO;
	}

	public ResultDTO updatePurchase(PurchaseDTO purchaseDTO, RequestDTO requestDTO) {
		
		Purchase purchase = purchaseDao.getById(purchaseDTO.getPurchaseId());

		purchase.setPurchaseId(ControllerUtils.setValue(purchase.getPurchaseId(), purchaseDTO.getPurchaseId()));

		purchase.setPurchaseDate(ControllerUtils.setValue(purchase.getPurchaseDate(), purchaseDTO.getPurchaseDate()));

		purchase.setAmount(ControllerUtils.setValue(purchase.getAmount(), purchaseDTO.getAmount()));



        purchase = purchaseDao.save(purchase);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PurchaseDTO getPurchaseDTOById(Integer purchaseId) {
	
		Purchase purchase = purchaseDao.getById(purchaseId);
			
		
		PurchaseConvertCriteriaDTO convertCriteria = new PurchaseConvertCriteriaDTO();
		return(this.convertPurchaseToPurchaseDTO(purchase,convertCriteria));
	}







}
