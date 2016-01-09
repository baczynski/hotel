package org.hotel.application.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hotel.application.ReservationDAO;
import org.hotel.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;

public class ReservationDAOImpl implements ReservationDAO {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Transactional
	@Override
	public void addReservation(Reservation c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(c);

	}

	@Transactional
	@Override
	public void updateReservation(Reservation c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(c);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> listReservations() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Reservation> ReservationsList = session.createQuery("from Reservation").list();

		return ReservationsList;
	}

	@Transactional
	@Override
	public Reservation getReservationByID(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Reservation c = (Reservation) session.load(Reservation.class, new Integer(id));
		return c;
	}

	@Transactional
	@Override
	public void removeReservation(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Reservation c = (Reservation) session.load(Reservation.class, new Integer(id));
		if (null != c) {
			session.delete(c);
		}

	}

}
