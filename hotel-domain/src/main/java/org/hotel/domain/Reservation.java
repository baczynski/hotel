package org.hotel.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Reservation")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "visitLength", nullable = false, unique = false)
	private int visitLength;
	@Column(name = "startVisitingDate", nullable = false, unique = false)
	private Calendar startVisitingDate;
	@Column(name = "fillFormDate", nullable = false, unique = false)
	private Calendar fillFormDate;
	@Column(name = "roomSide", nullable = false, unique = false)
	private String roomSide;

	@ManyToOne
	@JoinColumn(name = "klient_pesel", nullable = false)
	private Client client;

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

	public Calendar getFillFormDate() {
		return fillFormDate;
	}

	public void setFillFormDate(Calendar fillFormDate) {
		this.fillFormDate = fillFormDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

}
