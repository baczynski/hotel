package org.hotel.application.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
		Reservation r=null;
		try{
			Criteria criteria = session.createCriteria(Reservation.class);
			criteria.add(Restrictions.eq("id", id));
			r = (Reservation) criteria.uniqueResult();
		}
		catch(HibernateException e){
			e.printStackTrace();
		}
		return r;
	}

	@Transactional
	@Override
	public void removeReservation(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Reservation r = null;
		try{
			Criteria criteria = session.createCriteria(Reservation.class);
			criteria.add(Restrictions.eq("id", id));
			r = (Reservation) criteria.uniqueResult();
		}
		catch(HibernateException e){
			e.printStackTrace();
		}
		if (r != null)
			session.delete(r);

	}

}
