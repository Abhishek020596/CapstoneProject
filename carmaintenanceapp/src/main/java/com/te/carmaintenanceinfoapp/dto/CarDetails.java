package com.te.carmaintenanceinfoapp.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "carDetails")
public class CarDetails {

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "carId")
	private int carId;

	@Column(name = "carName")
	private String carName;

	@Column(name = "carCompany")
	private String carCompany;

	@Column(name = "carFuelType")
	private String carFuelType;

	@Column(name = "carPowerSteering")
	private Boolean carPowerSteering;

	@Column(name = "carBreakSystem")
	private String carBreakSystem;

	@Column(name = "carShowroomPrice")
	private Double carShowroomPrice;

	@Column(name = "carOnroadPrice")
	private Double carOnroadPrice;

	@Column(name = "imageUrl")
	private String ImageURL;

	@Column(name = "carMilege")
	private Double carMileage;

	@Column(name = "carSeatingCapacity")
	private int carSeatingCapacity;

	@Column(name = "carEngineCapacity")
	private int carEngineCapacity;

	@Column(name = "carGearType")
	private String carGearType;

	@ManyToOne()
	@JoinColumn(name = "Admin_Id", referencedColumnName = "username")
	private AdminDetails adminDetails;
}
