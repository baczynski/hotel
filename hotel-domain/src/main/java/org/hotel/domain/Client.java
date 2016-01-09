package org.hotel.domain;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Client")
public class Client {
	@Column(name="name",nullable=false,unique=false)
	private String name;
	@Column(name="surname",nullable=false,unique=false)
	private String surname;
	@Id
	@Column(name="pesel")
	private int pesel;
	@Column(name="adress",nullable=false,unique=false)
	private Address address;
	@Column(name="dateOfBirth",nullable=false,unique=false)
	private Calendar dateOfBirth;
	@Column(name="email",nullable=false,unique=true)
	private String email;
	@OneToMany(mappedBy="client")
	private List<Reservation> reservations;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getPesel() {
		return pesel;
	}
	public void setPesel(int pesel) {
		this.pesel = pesel;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Calendar dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	
	
}
