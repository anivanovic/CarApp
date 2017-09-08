package com.github.anivanovic.carapp.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CarOwner {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String firstName;
	private String lastName;
	private Date birthday;
	@OneToMany(mappedBy = "carOwner", cascade = CascadeType.PERSIST)
	private Collection<Car> carsOwned;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Collection<Car> getCarsOwned() {
		return carsOwned;
	}

	public void setCarsOwned(Collection<Car> carsOwned) {
		this.carsOwned = carsOwned;
	}
}
