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

import com.braggloopplace.domain.Watchlist;
import com.braggloopplace.dto.WatchlistDTO;
import com.braggloopplace.dto.WatchlistSearchDTO;
import com.braggloopplace.dto.WatchlistPageDTO;
import com.braggloopplace.service.WatchlistService;
import com.braggloopplace.dto.common.RequestDTO;
import com.braggloopplace.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/watchlist")
@RestController
public class WatchlistController {

	private final static Logger logger = LoggerFactory.getLogger(WatchlistController.class);

	@Autowired
	WatchlistService watchlistService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Watchlist> getAll() {

		List<Watchlist> watchlists = watchlistService.findAll();
		
		return watchlists;	
	}

	//@ReadAccess
	@GetMapping(value = "/{watchlistId}")
	@ResponseBody
	public WatchlistDTO getWatchlist(@PathVariable Integer watchlistId) {
		
		return (watchlistService.getWatchlistDTOById(watchlistId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addWatchlist", method = RequestMethod.POST)
	public ResponseEntity<?> addWatchlist(@RequestBody WatchlistDTO watchlistDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = watchlistService.addWatchlist(watchlistDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/watchlists")
	public ResponseEntity<WatchlistPageDTO> getWatchlists(WatchlistSearchDTO watchlistSearchDTO) {
 
		return watchlistService.getWatchlists(watchlistSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateWatchlist", method = RequestMethod.POST)
	public ResponseEntity<?> updateWatchlist(@RequestBody WatchlistDTO watchlistDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = watchlistService.updateWatchlist(watchlistDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
