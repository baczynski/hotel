package org.hotel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Damage")
public class Damage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "damageId")
	private int damageId;
	
	@OneToOne
	@JoinColumn(name = "reservationreservationId")
	private Reservation reservation;
	
	@Column(name="value")
	private double value;
	
	@Column(name="clientPay")
	private boolean clientPay;

	public int getDamageId() {
		return damageId;
	}

	public void setDamageId(int damageId) {
		this.damageId = damageId;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public boolean isClientPay() {
		return clientPay;
	}

	public void setClientPay(boolean clientPay) {
		this.clientPay = clientPay;
	}
	
	
 }
