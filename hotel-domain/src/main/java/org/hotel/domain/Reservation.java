package org.hotel.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Reservation")
public class Reservation implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservationId")
	private int reservationId;

	@Column(name = "visitLength", nullable = false, unique = false)
	private int visitLength;
	@Column(name = "fillFormDate", nullable = false, unique = false)
	private Calendar fillFormDate;
	@Column(name = "startVisitingDate", nullable = false, unique = false)
	private Calendar startVisitingDate;
	@Column(name = "peopleNumber", nullable = false, unique = false)
	private int peopleNumber;
	@Column(name = "roomSide", nullable = false, unique = false)
	private String roomSide;
	@Column(name="confirmed",nullable=false,unique=false)
	private boolean confirmed;

	@ManyToOne
	@JoinColumn(name = "clientid", nullable = false)
	private Client client;

	@OneToOne(mappedBy="reservation")
	private Accommodation accommodation;

	public int getVisitLength() {
		return visitLength;
	}

	public void setVisitLength(int visitLength) {
		this.visitLength = visitLength;
	}

	public Calendar getStartVisitingDate() {
		return startVisitingDate;
	}

	public void setStartVisitingDate(Calendar startVisitingDate) {
		this.startVisitingDate = startVisitingDate;
	}

	public int getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getRoomSide() {
		return roomSide;
	}

	public void setRoomSide(String roomSide) {
		this.roomSide = roomSide;
	}

	public Calendar getFillFormDate() {
		return fillFormDate;
	}

	public void setFillFormDate(Calendar fillFormDate) {
		this.fillFormDate = fillFormDate;
	}

	public Accommodation getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
}
