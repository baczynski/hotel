package org.hotel.application;

import java.util.List;

import org.hotel.domain.Price;

public interface PriceService {
	public void addPrice(Price p);

	public void updatePrice(Price p);

	public List<Price> listPrices();

	public Price getPriceByID(int id);

	public void removePrice(int id);
}
