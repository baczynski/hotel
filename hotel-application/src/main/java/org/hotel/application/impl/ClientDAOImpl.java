package org.hotel.application.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hotel.application.ClientDAO;
import org.hotel.domain.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientDAOImpl implements ClientDAO {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Transactional
	@Override
	public void addClient(Client c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(c);

	}

	@Transactional
	@Override
	public void updateClient(Client c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(c);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> listClients() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Client> clientsList = session.createQuery("from Client").list();

		return clientsList;
	}

	@Transactional
	@Override
	public Client getClientByPesel(int pesel) {
		Session session = this.sessionFactory.getCurrentSession();
		Client c = (Client) session.load(Client.class, new Integer(pesel));
		return c;
	}

	@Transactional
	@Override
	public void removeClient(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Client c = (Client) session.load(Client.class, new Integer(id));
		if (null != c) {
			session.delete(c);
		}

	}

}