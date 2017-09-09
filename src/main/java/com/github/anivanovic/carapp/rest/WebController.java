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

	@Autowired
	private CarOwnerRepository carOwnerRepository;
	@Autowired
	private CarModelRepository carModelRepository;
	@Autowired
	private CarRepository carRepository;

	@RequestMapping(value = "/carmodel/create", method = RequestMethod.POST)
	public ResponseEntity<String> createCarModel(@RequestBody CarModel carModel) {
		carModelRepository.save(carModel);
		return ResponseEntity.ok(SUCCESS_MESSAGE);
	}

	@RequestMapping(value = "/car/create", method = RequestMethod.POST)
	public ResponseEntity<String> createCar(@RequestBody Car car) {
		carRepository.save(car);
		return ResponseEntity.ok(SUCCESS_MESSAGE);
	}

	@RequestMapping(value = "/carowner/create", method = RequestMethod.POST)
	public ResponseEntity<String> createCarOwner(@RequestBody CarOwner carOwner) throws URISyntaxException {
		carOwnerRepository.save(carOwner);
		return ResponseEntity.created(new URI("/carapp/carowner/" + carOwner.getId())).build();
	}

	@RequestMapping(value = "/carowner/addCar", method = RequestMethod.POST)
	public ResponseEntity<String> addCarToOwner(@RequestBody String json) throws IOException {
		ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);
		long carId = node.get("carId").asLong();
		long carOwnerId = node.get("carOwnerId").asLong();
		Car car = carRepository.findOne(carId);
		CarOwner owner = new CarOwner();
		owner.setId(carOwnerId);
		car.setCarOwner(owner);
		carRepository.save(car);
		return ResponseEntity.ok(SUCCESS_MESSAGE);
	}

	@RequestMapping(value = "/carowner/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<CarOwner> getCarOwner(@PathVariable Long id) {
		if (carOwnerRepository.exists(id)) {
			CarOwner owner = carOwnerRepository.getOne(id);
			return ResponseEntity.ok(owner);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
