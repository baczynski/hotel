package org.hotel.application.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hotel.application.DamageDAO;
import org.hotel.domain.Damage;
import org.springframework.beans.factory.annotation.Autowired;

public class DamageDAOImpl implements DamageDAO {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Transactional
	@Override
	public void addDamage(Damage d) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(d);
	}

	@Transactional
	@Override
	public void updateDamage(Damage d) {
		Session session = sessionFactory.getCurrentSession();
		session.update(d);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<Damage> listDamages() {
		Session session = sessionFactory.getCurrentSession();
		List<Damage> list = session.createQuery("from Damage").list();
		return list;
	}

	@Transactional
	@Override
	public Damage getDamageById(int damageId) {
		Session session = sessionFactory.getCurrentSession();
		Damage d = null;
		try {
			Criteria criteria = session.createCriteria(Damage.class);
			criteria.add(Restrictions.eq("damageId", damageId));
			d = (Damage) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return d;
	}

	@Transactional
	@Override
	public void removeDamage(int damageId) {
		Session session = sessionFactory.getCurrentSession();
		Damage d = null;
		try {
			Criteria criteria = session.createCriteria(Damage.class);
			criteria.add(Restrictions.eq("damageId", damageId));
			d = (Damage) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		if (d != null) {
			session.delete(d);
		}
	}

}
