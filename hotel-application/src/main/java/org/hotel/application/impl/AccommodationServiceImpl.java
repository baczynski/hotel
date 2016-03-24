package org.hotel.application.impl;

import java.util.List;

import org.hotel.application.AccommodationDAO;
import org.hotel.application.AccommodationService;
import org.hotel.domain.Accommodation;

public class AccommodationServiceImpl implements AccommodationService {
	
	private AccommodationDAO accommodationDAO;
	@Override
	public void addAccommodation(Accommodation a) {
		accommodationDAO.addAccommodation(a);
	}

	@Override
	public void updateAccommodation(Accommodation a) {
		accommodationDAO.updateAccommodation(a);

	}

	@Override
	public List<Accommodation> listAccommodations() {
		return accommodationDAO.listAccommodations();
	}

	@Override
	public Accommodation getAccommodationByReservationId(int reservationId) {
		return accommodationDAO.getAccommodationByReservationId(reservationId);
	}

	@Override
	public void removeAccommodation(int reservationId) {
		accommodationDAO.removeAccommodation(reservationId);

	}


	public void setAccommodationDAO(AccommodationDAO accommodationDAO) {
		this.accommodationDAO = accommodationDAO;
	}
	
}
