package org.hotel.application.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hotel.application.PriceDAO;
import org.hotel.domain.Price;
import org.springframework.beans.factory.annotation.Autowired;

public class PriceDAOImpl implements PriceDAO {
	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Transactional
	@Override
	public void addPrice(Price p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);

	}

	@Transactional
	@Override
	public void updatePrice(Price p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<Price> listPrices() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Price> PricesList = session.createQuery("from Price").list();

		return PricesList;
	}

	@Transactional
	@Override
	public Price getPriceByID(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Price p = (Price) session.load(Price.class, new Integer(id));
		return p;
	}

	@Transactional
	@Override
	public void removePrice(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Price p = (Price) session.load(Price.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}

	}
}
