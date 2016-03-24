package org.hotel.application;

import java.util.List;

import org.hotel.domain.Client;

public interface ClientService {
	public void addClient(Client c);

	public void updateClient(Client c);

	public List<Client> listClients();

	public Client getClientByEmail(String email);

	public void removeClient(String email);
	
}
