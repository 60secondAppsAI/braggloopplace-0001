package com.braggloopplace.dao;

import java.util.List;

import com.braggloopplace.dao.GenericDAO;
import com.braggloopplace.domain.User;

import java.util.Optional;




public interface UserDAO extends GenericDAO<User, Integer> {
  
	List<User> findAll();
	






}


