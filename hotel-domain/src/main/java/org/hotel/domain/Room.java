package org.hotel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Room")
public class Room {
	@Id
	@Column(name="roomNumber",nullable=false)
	private int roomNumber;
	@Column(name="maxPeople",nullable=false)
	private int maxPeople;
	@Column(name="view",nullable=false)
	private String view;
	@Column(name="roomState",nullable=false)
	@Enumerated(value=EnumType.STRING)
	private RoomState roomState;
	
	
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public int getMaxPeople() {
		return maxPeople;
	}
	public void setMaxPeople(int maxPeople) {
		this.maxPeople = maxPeople;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public RoomState getRoomState() {
		return roomState;
	}
	public void setRoomState(RoomState roomState) {
		this.roomState = roomState;
	}
		
	
}
