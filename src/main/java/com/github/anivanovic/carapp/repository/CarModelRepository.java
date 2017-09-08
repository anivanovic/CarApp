package com.github.anivanovic.carapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.anivanovic.carapp.entity.CarModel;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {

}
