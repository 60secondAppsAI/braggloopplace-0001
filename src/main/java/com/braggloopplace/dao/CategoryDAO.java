package com.braggloopplace.dao;

import java.util.List;

import com.braggloopplace.dao.GenericDAO;
import com.braggloopplace.domain.Category;





public interface CategoryDAO extends GenericDAO<Category, Integer> {
  
	List<Category> findAll();
	






}


