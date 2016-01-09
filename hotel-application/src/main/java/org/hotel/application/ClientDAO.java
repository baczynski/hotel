package org.hotel.application;

import java.util.List;

import org.hotel.domain.Client;

public interface ClientDAO {
	public void addClient(Client c);

	public void updateClient(Client c);

	public List<Client> listClients();

	public Client getClientByPesel(int pesel);

	public void removeClient(int pesel);
}
