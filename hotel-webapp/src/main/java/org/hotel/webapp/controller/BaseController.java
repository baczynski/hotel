//package org.hotel.webapp.controller;
//
//import java.util.GregorianCalendar;
//
//import org.hotel.application.AccommodationService;
//import org.hotel.application.ClientService;
//import org.hotel.application.PriceService;
//import org.hotel.application.ReservationService;
//import org.hotel.application.RoomService;
//import org.hotel.domain.Accommodation;
//import org.hotel.domain.Address;
//import org.hotel.domain.Client;
//import org.hotel.domain.Price;
//import org.hotel.domain.Reservation;
//import org.hotel.domain.Room;
//import org.hotel.domain.RoomState;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@Controller
//public class BaseController {
//	Logger logger = LoggerFactory.getLogger(BaseController.class);
//	@Autowired
//	private ReservationService reservationService;
//	@Autowired
//	private ClientService clientService;
//	@Autowired
//	private PriceService priceService;
//	@Autowired
//	private RoomService roomService;
//	@Autowired
//	private AccommodationService accommodationServide;
//
//
////	@RequestMapping(value = "/", method = RequestMethod.GET)
////	public String displayMain(Model model) {
////		//createRoom();
////		// createRoom();
////		// createPrice();
////		// createClient();
////		// createReservation();
////		// createProducts();
////		// createEmployees();
////		// createClients();
////		// createAddresses();
////		// createDates();
////		// createOrders();
////		// createOrderContents();
////		// System.out.println(orderService.getOrderById(1).getOrderState());
////		return "index";
////	}
//
//	
//	private void createClient() {
//		Client c = new Client();
//		c.setName("Konrad");
//		c.setSurname("Bączyński");
//		c.setPhoneNumber("666-666-666");
//		;
//		c.setEmail("kkk@kkk.com");
//		c.setAddress(new Address("Miarki 12", "Wrocław", "42-230"));
//		c.setDateOfBirth(new GregorianCalendar(2015, 0, 5));
//		clientService.addClient(c);
//	}
//
//	private void createReservation() {
//		Reservation r = new Reservation();
//		r.setStartVisitingDate(new GregorianCalendar(2016, 2, 3));
//		r.setVisitLength(10);
//		r.setRoomSide("morze");
//		r.setPeopleNumber(4);
//		r.setClient(clientService.getClientByEmail("kkk@kkk.com"));
//		reservationService.addReservation(r);
//	}
//
//	private void createPrice() {
//		Price p = new Price();
//		p.setPrice(40);
//		p.setBeginDate(new GregorianCalendar(2016, 8, 1));
//		p.setEndDate(new GregorianCalendar(2016, 8, 30));
//		priceService.addPrice(p);
//		Price p1 = new Price();
//		p1.setPrice(20);
//		p1.setBeginDate(new GregorianCalendar(2016, 1, 1));
//		p1.setEndDate(new GregorianCalendar(2016, 5, 31));
//		priceService.addPrice(p1);
//		Price p2 = new Price();
//		p2.setPrice(60);
//		p2.setBeginDate(new GregorianCalendar(2016, 6, 1));
//		p2.setEndDate(new GregorianCalendar(2016, 7, 31));
//		priceService.addPrice(p2);
//		Price p3 = new Price();
//		p3.setPrice(40);
//		p3.setBeginDate(new GregorianCalendar(2016, 9, 1));
//		p3.setEndDate(new GregorianCalendar(2016, 12, 31));
//		priceService.addPrice(p3);
//	}
//
//	private void createRoom() {
//		Room r1 = new Room();
//		r1.setRoomNumber(1);
//		r1.setMaxPeople(4);
//		r1.setRoomState(RoomState.CLEANED);
//		r1.setView("sea");
//		roomService.addRoom(r1);
//		
//		Room r2 = new Room();
//		r2.setRoomNumber(2);
//		r2.setMaxPeople(4);
//		r2.setRoomState(RoomState.CLEANED);
//		r2.setView("park");
//		roomService.addRoom(r2);
//		
//		Room r3 = new Room();
//		r3.setRoomNumber(3);
//		r3.setMaxPeople(4);
//		r3.setRoomState(RoomState.CLEANED);
//		r3.setView("park");
//		roomService.addRoom(r3);
//		
//		
//		Room r4 = new Room();
//		r4.setRoomNumber(4);
//		r4.setMaxPeople(4);
//		r4.setRoomState(RoomState.CLEANED);
//		r4.setView("sea");
//		roomService.addRoom(r4);
//		
//		Room r5 = new Room();
//		r5.setRoomNumber(5);
//		r5.setMaxPeople(2);
//		r5.setRoomState(RoomState.CLEANED);
//		r5.setView("sea");
//		roomService.addRoom(r5);
//		
//		Room r6 = new Room();
//		r6.setRoomNumber(6);
//		r6.setMaxPeople(2);
//		r6.setRoomState(RoomState.CLEANED);
//		r6.setView("park");
//		roomService.addRoom(r6);
//		
//		Room r7 = new Room();
//		r7.setRoomNumber(7);
//		r7.setMaxPeople(2);
//		r7.setRoomState(RoomState.CLEANED);
//		r7.setView("sea");
//		roomService.addRoom(r7);
//		
//		Room r8 = new Room();
//		r8.setRoomNumber(8);
//		r8.setMaxPeople(3);
//		r8.setRoomState(RoomState.CLEANED);
//		r8.setView("park");
//		roomService.addRoom(r8);
//		
//		Room r9 = new Room();
//		r9.setRoomNumber(9);
//		r9.setMaxPeople(3);
//		r9.setRoomState(RoomState.CLEANED);
//		r9.setView("sea");
//		roomService.addRoom(r9);
//		
//		Room r10 = new Room();
//		r10.setRoomNumber(10);
//		r10.setMaxPeople(5);
//		r10.setRoomState(RoomState.CLEANED);
//		r10.setView("sea");
//		roomService.addRoom(r10);
//	}
//	
//	
//	private void createAccommodation(){
//		Accommodation a = new Accommodation();
//	}
//}