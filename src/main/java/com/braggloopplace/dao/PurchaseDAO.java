package com.braggloopplace.dao;

import java.util.List;

import com.braggloopplace.dao.GenericDAO;
import com.braggloopplace.domain.Purchase;





public interface PurchaseDAO extends GenericDAO<Purchase, Integer> {
  
	List<Purchase> findAll();
	






}


