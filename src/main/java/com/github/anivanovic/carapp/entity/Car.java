package com.github.anivanovic.carapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String vehcileIdentificationNumber;
	private String color;
	private Double price;
	@ManyToOne
	@JoinColumn(nullable = true)
	private CarOwner carOwner;

	@ManyToOne
	private CarModel carModel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CarModel getCarModel() {
		return carModel;
	}

	public void setCarModel(CarModel carModel) {
		this.carModel = carModel;
	}

	public String getVehcileIdentificationNumber() {
		return vehcileIdentificationNumber;
	}

	public void setVehcileIdentificationNumber(String vehcileIdentificationNumber) {
		this.vehcileIdentificationNumber = vehcileIdentificationNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public CarOwner getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(CarOwner carOwner) {
		this.carOwner = carOwner;
	}
}
