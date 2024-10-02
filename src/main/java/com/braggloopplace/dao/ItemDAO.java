package com.braggloopplace.dao;

import java.util.List;

import com.braggloopplace.dao.GenericDAO;
import com.braggloopplace.domain.Item;





public interface ItemDAO extends GenericDAO<Item, Integer> {
  
	List<Item> findAll();
	






}


