package org.hotel.application;

import java.util.List;

import org.hotel.domain.Room;

public interface RoomDAO {
	public void addRoom(Room r);

	public void updateRoom(Room r);

	public List<Room> listRooms();

	public Room getRoomByRoomNumber(int roomNumber);

	public void removeRoom(int roomNumber);
}
