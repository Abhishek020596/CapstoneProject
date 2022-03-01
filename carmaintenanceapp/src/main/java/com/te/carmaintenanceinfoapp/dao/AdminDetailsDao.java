package com.te.carmaintenanceinfoapp.dao;

import org.springframework.data.repository.CrudRepository;

import com.te.carmaintenanceinfoapp.dto.AdminDetails;

public interface AdminDetailsDao extends CrudRepository<AdminDetails, Integer>{
	public AdminDetails findById(int adminId);
	
	public AdminDetails findByUsername(String username);
}
