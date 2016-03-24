package org.hotel.application.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hotel.application.AccommodationDAO;
import org.hotel.domain.Accommodation;
import org.hotel.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;

public class AccommodationDAOImpl implements AccommodationDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Transactional
	@Override
	public void addAccommodation(Accommodation a) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(a);

	}
	@Transactional
	@Override
	public void updateAccommodation(Accommodation a) {
		Session session = sessionFactory.getCurrentSession();
		session.update(a);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<Accommodation> listAccommodations() {
		Session session = sessionFactory.getCurrentSession();
		List<Accommodation> list = session.createQuery("from Accommodation").list();
		return list;
	}
	@Transactional
	@Override
	public Accommodation getAccommodationByReservationId(int reservationId) {
		Accommodation a=null;
		List<Accommodation> list = listAccommodations();
		for(Accommodation ac: list){
			if(ac.getReservation().getReservationId()==reservationId){
				a=ac;
			}
		}
		return a;
	}
	@Transactional
	@Override
	public void removeAccommodation(int reservationId) {
		Session session = sessionFactory.getCurrentSession();
		Accommodation a=null;
		try{
			Criteria criteria = session.createCriteria(Accommodation.class);
			criteria.add(Restrictions.eq("id", reservationId));
			a= (Accommodation) criteria.uniqueResult();
		}
		catch(HibernateException e){
			e.printStackTrace();
		}
		if(a!=null)
			session.delete(a);
	}

}
