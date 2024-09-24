package com.cache.RedisCache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cache.RedisCache.model.ReservationVO;
import com.cache.RedisCache.service.RedisService;
import com.cache.RedisCache.service.ReservationService;

@RestController
public class ReservationController
{
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private RedisService redisService;
	
	@GetMapping("/test")
	public String testRedis()
	{
		redisService.set("name", "sovon", 1);
		return redisService.get("name", String.class);
	}
	
	@GetMapping("/reservation/resID/{resID}")
	public ReservationVO getResByID(@PathVariable("resID") Long resID)
	{
		ReservationVO reservationVO = redisService.get(Long.toString(resID), ReservationVO.class);
		if(null==reservationVO)
		{
			ReservationVO resVOData = reservationService.getResData(resID);
			if(null!=resVOData)
			{
				redisService.set(Long.toString(resID), resVOData, 60);
			}
		}
		return reservationVO;
	}
	
	@PostMapping("/reservation")
	public ReservationVO saveRes(@RequestBody ReservationVO resVO)
	{
		return reservationService.saveReservationData(resVO);
	}
}
