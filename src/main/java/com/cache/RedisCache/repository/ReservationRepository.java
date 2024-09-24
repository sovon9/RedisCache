package com.cache.RedisCache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cache.RedisCache.model.ReservationVO;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationVO, Long>{

	ReservationVO findByStatus(String status);

}
