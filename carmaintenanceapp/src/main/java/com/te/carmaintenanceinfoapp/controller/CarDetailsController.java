package com.te.carmaintenanceinfoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.carmaintenanceinfoapp.dto.AdminDetails;
import com.te.carmaintenanceinfoapp.dto.AdminRes;
import com.te.carmaintenanceinfoapp.dto.CarDetails;
import com.te.carmaintenanceinfoapp.jwt.JwtUtil;
import com.te.carmaintenanceinfoapp.model.AuthReq;
import com.te.carmaintenanceinfoapp.model.AuthRes;
import com.te.carmaintenanceinfoapp.service.AdminDetailsServiceImpl;

@RestController
@CrossOrigin(origins = "*")
public class CarDetailsController {

	@Autowired
	private AdminDetailsServiceImpl service;

	@Autowired
	private AuthenticationManager authenticateManager;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@PostMapping("/adminReg")
	public ResponseEntity<?> createRegistration(@RequestBody AdminDetails admin) {
		AdminDetails createRegistration = service.createRegistration(admin);
		if (createRegistration != null) {
			authenticateManager.authenticate(new UsernamePasswordAuthenticationToken(createRegistration.getUsername(),
					createRegistration.getPassword()));

			UserDetails userDetails = service.loadUserByUsername(createRegistration.getUsername());
			String jwtToken = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok(new AdminRes(false, "Success", jwtToken));
		} else {
			return ResponseEntity.ok(new AdminRes(true, "Username Already Exists ", null));
		}
	}

	@PostMapping("/adminloginauth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthReq authenticationRequest) throws Exception {
		try {
			authenticateManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or Password", e);

		}
		final UserDetails userDetails = service.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthRes(jwt));

	}

	@GetMapping(path = { "/allcar" })
	public ResponseEntity<?> cardetails() {
		try {
			List<CarDetails> carDetails = service.carDetailsList();
			return new ResponseEntity<List<CarDetails>>(carDetails, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Something Went Wrong !", HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@PostMapping(path = "/CarDetails")
	public ResponseEntity<?> addDetails(@RequestBody CarDetails car) {
		try {
			CarDetails addCars = service.addCarDetails(car);
			return new ResponseEntity<String>("Data is Inserted !", HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<String>("Something Went Wrong !", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/CarDetails/{carId}")
	public ResponseEntity<?> deleteDetails(@PathVariable int carId) {
		try {
			service.deleteCarDetails(carId);
			return new ResponseEntity<String>("Car Details is Deleted", HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<String>("Something went Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
