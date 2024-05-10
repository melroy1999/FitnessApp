package com.fitness.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitness.tracker.entity.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer>{
	
	@Query(nativeQuery = true,value = "select name from Device where user_id=?1")
	public String findDeviceNameByUserId(Integer userId);
	

}
