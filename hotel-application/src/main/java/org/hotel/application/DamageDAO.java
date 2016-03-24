package org.hotel.application;

import java.util.List;

import org.hotel.domain.Damage;

public interface DamageDAO {

	public void addDamage(Damage d);

	public void updateDamage(Damage d);

	public List<Damage> listDamages();

	public Damage getDamageById(int damageId);

	public void removeDamage(int damageId);
}
