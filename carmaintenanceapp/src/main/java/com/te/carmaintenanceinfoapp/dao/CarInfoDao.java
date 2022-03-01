package com.te.carmaintenanceinfoapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.te.carmaintenanceinfoapp.dto.CarDetails;

@Repository
public interface CarInfoDao extends CrudRepository<CarDetails, Integer>{
	public CarDetails findById(int carId);
}
