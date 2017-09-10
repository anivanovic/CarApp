package com.github.anivanovic.carapp.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.anivanovic.carapp.entity.Car;
import com.github.anivanovic.carapp.entity.CarModel;
import com.github.anivanovic.carapp.entity.CarOwner;
import com.github.anivanovic.carapp.repository.CarModelRepository;
import com.github.anivanovic.carapp.repository.CarOwnerRepository;
import com.github.anivanovic.carapp.repository.CarRepository;

@RestController
public class WebController {

	private static final String SUCCESS_MESSAGE = "Successfuly created resource.";

	private CarOwnerRepository carOwnerRepository;
	private CarModelRepository carModelRepository;
	private CarRepository carRepository;

	public CarOwnerRepository getCarOwnerRepository() {
		return carOwnerRepository;
	}

	@Autowired
	public void setCarOwnerRepository(CarOwnerRepository carOwnerRepository) {
		this.carOwnerRepository = carOwnerRepository;
	}

	public CarModelRepository getCarModelRepository() {
		return carModelRepository;
	}

	@Autowired
	public void setCarModelRepository(CarModelRepository carModelRepository) {
		this.carModelRepository = carModelRepository;
	}

	public CarRepository getCarRepository() {
		return carRepository;
	}

	@Autowired
	public void setCarRepository(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@RequestMapping(value = "/carmodel/create", method = RequestMethod.POST)
	public ResponseEntity<String> createCarModel(@RequestBody CarModel carModel) {
		getCarModelRepository().save(carModel);
		return ResponseEntity.ok(SUCCESS_MESSAGE);
	}

	@RequestMapping(value = "/car/create", method = RequestMethod.POST)
	public ResponseEntity<String> createCar(@RequestBody Car car) {
		getCarRepository().save(car);
		return ResponseEntity.ok(SUCCESS_MESSAGE);
	}

	@RequestMapping(value = "/carowner/create", method = RequestMethod.POST)
	public ResponseEntity<String> createCarOwner(@RequestBody CarOwner carOwner)
			throws URISyntaxException {
		getCarOwnerRepository().save(carOwner);
		return ResponseEntity.created(new URI("/carapp/carowner/" + carOwner.getId())).build();
	}

	@RequestMapping(value = "/carowner/addCar", method = RequestMethod.POST)
	public ResponseEntity<String> addCarToOwner(@RequestBody String json) throws IOException {
		ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);
		JsonNode carNode = node.get("carId");
		JsonNode carOwnerNode = node.get("carOwnerId");
		if (carNode == null || carOwnerNode == null) {
			return ResponseEntity.status(400).body("CarId or carOwnerId parameter not found");
		}

		long carId = carNode.asLong(-1);
		long carOwnerId = carOwnerNode.asLong(-1);
		Car car = getCarRepository().findOne(carId);
		if (car == null) {
			return ResponseEntity.status(404).body("Car not found");
		}

		boolean ownerExists = getCarOwnerRepository().exists(carOwnerId);
		if (!ownerExists) {
			return ResponseEntity.status(404).body("Car owner not found");
		}

		CarOwner owner = new CarOwner();
		owner.setId(carOwnerId);
		car.setCarOwner(owner);
		getCarRepository().save(car);
		return ResponseEntity.ok(SUCCESS_MESSAGE);
	}

	@RequestMapping(value = "/carowner/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<CarOwner> getCarOwner(@PathVariable Long id) {
		if (getCarOwnerRepository().exists(id)) {
			CarOwner owner = getCarOwnerRepository().getOne(id);
			return ResponseEntity.ok(owner);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
