package com.github.anivanovic.carapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.anivanovic.carapp.entity.CarOwner;

public interface CarOwnerRepository extends JpaRepository<CarOwner, Long> {

}
