package org.hotel.application.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hotel.application.RoomDAO;
import org.hotel.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;

public class RoomDAOImpl implements RoomDAO {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Transactional
	@Override
	public void addRoom(Room r) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(r);

	}

	@Transactional
	@Override
	public void updateRoom(Room r) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(r);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<Room> listRooms() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Room> RoomsList = (List<Room>)session.createQuery("from Room").list();

		return RoomsList;
	}

	@Transactional
	@Override
	public Room getRoomByRoomNumber(int roomNumber) {
		Session session = this.sessionFactory.getCurrentSession();
		Room r = null;
		try{
			Criteria criteria = session.createCriteria(Room.class);
			criteria.add(Restrictions.eq("roomNumber", roomNumber));
			r = (Room) criteria.uniqueResult();
		}
		catch(HibernateException e){
			e.printStackTrace();
		}
		return r;
	}

	@Transactional
	@Override
	public void removeRoom(int roomNumber) {
		Session session = this.sessionFactory.getCurrentSession();
		Room r = null;
		try{
			Criteria criteria = session.createCriteria(Room.class);
			criteria.add(Restrictions.eq("roomNumber", roomNumber));
			r = (Room) criteria.uniqueResult();
		}
		catch(HibernateException e){
			e.printStackTrace();
		}
		if(r!=null){
			session.delete(r);
		}

	}

}
