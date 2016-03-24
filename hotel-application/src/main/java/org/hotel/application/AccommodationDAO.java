package org.hotel.application;

import java.util.List;

import org.hotel.domain.Accommodation;

public interface AccommodationDAO {

	public void addAccommodation(Accommodation a);

	public void updateAccommodation(Accommodation a);

	public List<Accommodation> listAccommodations();

	public Accommodation getAccommodationByReservationId(int reservationId);

	public void removeAccommodation(int reservationId);
}
