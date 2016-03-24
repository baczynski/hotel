package org.hotel.application.impl;

import java.util.List;

import org.hotel.application.RoomDAO;
import org.hotel.application.RoomService;
import org.hotel.domain.Room;

public class RoomServiceImpl implements RoomService {
	private RoomDAO roomDAO;
	@Override
	public void addRoom(Room r) {
		roomDAO.addRoom(r);

	}

	@Override
	public void updateRoom(Room r) {
		roomDAO.updateRoom(r);

	}

	@Override
	public List<Room> listRooms() {
		return roomDAO.listRooms();
	}

	@Override
	public Room getRoomByRoomNumber(int roomNumber) {
		return roomDAO.getRoomByRoomNumber(roomNumber);
	}

	@Override
	public void removeRoom(int roomNumber) {
		roomDAO.removeRoom(roomNumber);

	}

	public RoomDAO getRoomDAO() {
		return roomDAO;
	}

	public void setRoomDAO(RoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}
}
