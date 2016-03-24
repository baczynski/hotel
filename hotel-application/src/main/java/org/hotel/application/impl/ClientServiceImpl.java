package org.hotel.application.impl;

import java.util.List;

import org.hotel.application.ClientDAO;
import org.hotel.application.ClientService;
import org.hotel.domain.Client;

public class ClientServiceImpl implements ClientService {
	private ClientDAO clientDAO;
	@Override
	public void addClient(Client c) {
		clientDAO.addClient(c);

	}

	@Override
	public void updateClient(Client c) {
		clientDAO.updateClient(c);;

	}

	@Override
	public List<Client> listClients() {
		return clientDAO.listClients();
	}

	@Override
	public Client getClientByEmail(String email) {
		return clientDAO.getClientByEmail(email);
	}

	@Override
	public void removeClient(String email) {
		clientDAO.removeClient(email);

	}

	public ClientDAO getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}
	
}
