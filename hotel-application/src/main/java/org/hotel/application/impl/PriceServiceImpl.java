package org.hotel.application.impl;

import java.util.List;

import org.hotel.application.PriceDAO;
import org.hotel.application.PriceService;
import org.hotel.domain.Price;

public class PriceServiceImpl implements PriceService {
	private PriceDAO priceDAO;
	@Override
	public void addPrice(Price p) {
		priceDAO.addPrice(p);

	}

	@Override
	public void updatePrice(Price p) {
		priceDAO.updatePrice(p);

	}

	@Override
	public List<Price> listPrices() {
		return priceDAO.listPrices();
	}

	@Override
	public Price getPriceByID(int id) {
		return priceDAO.getPriceByID(id);
	}

	@Override
	public void removePrice(int id) {
		priceDAO.removePrice(id);

	}

	public PriceDAO getPriceDAO() {
		return priceDAO;
	}

	public void setPriceDAO(PriceDAO priceDAO) {
		this.priceDAO = priceDAO;
	}
}
