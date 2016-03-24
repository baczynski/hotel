package org.hotel.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Accommodation")
public class Accommodation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="accommodationId")
	private int accommodationId;
	
	@OneToOne
	@JoinColumn(name = "reservationreservationId")
	private Reservation reservation;

	@OneToOne
	@JoinColumn(name = "roomroomNumber")
	private Room room;

	@Column(name = "reservationState")
	private ReservationState reservationState;

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public ReservationState getReservationState() {
		return reservationState;
	}

	public void setReservationState(ReservationState reservationState) {
		this.reservationState = reservationState;
	}

	public int getAccommodationId() {
		return accommodationId;
	}

	public void setAccommodationId(int accommodationId) {
		this.accommodationId = accommodationId;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
}
