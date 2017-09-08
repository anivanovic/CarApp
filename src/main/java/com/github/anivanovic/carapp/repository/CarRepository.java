package com.github.anivanovic.carapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.anivanovic.carapp.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
