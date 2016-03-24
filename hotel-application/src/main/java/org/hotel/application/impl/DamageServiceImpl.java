package org.hotel.application.impl;

import java.util.List;

import org.hotel.application.DamageDAO;
import org.hotel.application.DamageService;
import org.hotel.domain.Damage;

public class DamageServiceImpl implements DamageService {
	
	DamageDAO damageDAO;
	
	@Override
	public void addDamage(Damage d) {
		damageDAO.addDamage(d);
		
	}

	@Override
	public void updateDamage(Damage d) {
		damageDAO.updateDamage(d);
		
	}

	@Override
	public List<Damage> listDamages() {
		return damageDAO.listDamages();
	}

	@Override
	public Damage getDamageById(int damageId) {
		return damageDAO.getDamageById(damageId);
	}

	@Override
	public void removeDamage(int damageId) {
		damageDAO.removeDamage(damageId);
		
	}

	public void setDamageDAO(DamageDAO damageDAO) {
		this.damageDAO = damageDAO;
	}
	
}
