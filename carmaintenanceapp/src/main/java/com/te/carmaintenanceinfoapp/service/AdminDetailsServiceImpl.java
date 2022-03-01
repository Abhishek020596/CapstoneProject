package com.te.carmaintenanceinfoapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.te.carmaintenanceinfoapp.dao.AdminDetailsDao;
import com.te.carmaintenanceinfoapp.dao.CarInfoDao;
import com.te.carmaintenanceinfoapp.dto.AdminDetails;
import com.te.carmaintenanceinfoapp.dto.CarDetails;
import com.te.carmaintenanceinfoapp.dto.MyAdminDetails;

public class AdminDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private AdminDetailsDao adminDetailsDao;
	
	@Autowired
	private CarInfoDao carDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminDetails adminName= adminDetailsDao.findByUsername(username);
		if(adminName!=null) {
			return new MyAdminDetails(adminName);
		}
		throw new UsernameNotFoundException("Admin is not found");
	}
	
	public AdminDetails createRegistration(AdminDetails adminDetails) {
		return adminDetailsDao.save(adminDetails);	
	}
	
	public List<CarDetails> carDetailsList(){
		return (List<CarDetails>) carDao.findAll();
	}
	
	public CarDetails addCarDetails(CarDetails carDetails) {
		return carDao.save(carDetails);
	}
	
	public void deleteCarDetails(int carId) {
		CarDetails findByCarId=carDao.findById(carId);
		
		carDao.delete(findByCarId);
	} 
}
