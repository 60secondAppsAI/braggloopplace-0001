package com.braggloopplace.dao;

import java.util.List;

import com.braggloopplace.dao.GenericDAO;
import com.braggloopplace.domain.Feedback;





public interface FeedbackDAO extends GenericDAO<Feedback, Integer> {
  
	List<Feedback> findAll();
	






}


