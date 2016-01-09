package org.hotel.webapp.controller;

import java.util.GregorianCalendar;

import org.hotel.application.ClientService;
import org.hotel.application.ReservationService;
import org.hotel.domain.Address;
import org.hotel.domain.Client;
import org.hotel.domain.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BaseController {
	Logger logger = LoggerFactory.getLogger(BaseController.class);
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private ClientService clientService;
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayMain(Model model) {
		//createClients();
		//createReservation();
		//createProducts();
		//createEmployees();
		//createClients();
		//createAddresses();
		//createDates();
		//createOrders();
		//createOrderContents();
		//System.out.println(orderService.getOrderById(1).getOrderState());
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String displayLogin(Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/product_details", method = RequestMethod.GET)
	public String displayProductDetails(Model model) {
		return "product_details";
	}
	@RequestMapping(value = "/basket", method = RequestMethod.GET)
	public String displayBasket(Model model) {
		return "basket";
	}
	
	@RequestMapping(value = "/confirmation", method = RequestMethod.GET)
	public String displayConfirmation(Model model) {
		return "confirmation";
	}
	
	@SuppressWarnings("deprecation")
	private void createClients(){
		Client c = new Client();
		c.setName("Konrad");
		c.setSurname("Bączyński");
		c.setPesel(999999999);
		c.setEmail("dddd");
		c.setAddress(new Address("Miarki 12", "Wrocław", "42-230"));
		c.setDateOfBirth(new GregorianCalendar(2015,0,5));
		clientService.addClient(c);
	}
	private void createReservation(){
		Reservation r = new Reservation();
		r.setFillFormDate(new GregorianCalendar(2018,0,5));
		r.setStartVisitingDate(new GregorianCalendar(2016,2,3));
		r.setVisitLength(10);
		r.setRoomSide("morze");
		r.setClient(clientService.getClientByPesel(999999999));
		reservationService.addReservation(r);
	}
	
}