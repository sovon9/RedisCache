package com.cache.RedisCache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cache.RedisCache.model.ReservationVO;
import com.cache.RedisCache.repository.ReservationRepository;

/**
 * 
 * @author Sovon Singha
 *
 */
@Service
public class ReservationService {

	@Autowired
	private ReservationRepository repository;
	
	public ReservationVO saveReservationData(ReservationVO resVO)
	{
		return repository.save(resVO);
	}

	public ReservationVO getResData(Long resID) {
		return repository.findById(resID).orElse(null);
	}

	public ReservationVO fetchReservationData(String status)
	{
		return repository.findByStatus(status);
	}
	
}
