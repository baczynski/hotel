package org.hotel.application;

import java.util.List;

import org.hotel.domain.Reservation;

public interface ReservationDAO {
	public void addReservation(Reservation c);

	public void updateReservation(Reservation c);

	public List<Reservation> listReservations();

	public Reservation getReservationByID(int id);

	public void removeReservation(int id);
}
