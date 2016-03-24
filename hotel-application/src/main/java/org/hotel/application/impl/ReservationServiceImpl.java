package org.hotel.application.impl;

import java.util.List;

import org.hotel.application.ReservationDAO;
import org.hotel.application.ReservationService;
import org.hotel.domain.Reservation;

public class ReservationServiceImpl implements ReservationService {
	private ReservationDAO reservationDAO;
	@Override
	public void addReservation(Reservation c) {
		reservationDAO.addReservation(c);

	}

	@Override
	public void updateReservation(Reservation c) {
		reservationDAO.updateReservation(c);

	}

	@Override
	public List<Reservation> listReservations() {
		return reservationDAO.listReservations();
		
	}

	@Override
	public Reservation getReservationByID(int id) {
		return reservationDAO.getReservationByID(id);
	}

	@Override
	public void removeReservation(int id) {
		reservationDAO.removeReservation(id);

	}

	public ReservationDAO getReservationDAO() {
		return reservationDAO;
	}

	public void setReservationDAO(ReservationDAO reservationDAO) {
		this.reservationDAO = reservationDAO;
	}
	
}
