package com.braggloopplace.dao;

import java.util.List;

import com.braggloopplace.dao.GenericDAO;
import com.braggloopplace.domain.Watchlist;





public interface WatchlistDAO extends GenericDAO<Watchlist, Integer> {
  
	List<Watchlist> findAll();
	






}


