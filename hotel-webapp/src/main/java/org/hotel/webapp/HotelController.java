package org.hotel.webapp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hotel.application.AccommodationService;
import org.hotel.application.ClientService;
import org.hotel.application.DamageService;
import org.hotel.application.PriceService;
import org.hotel.application.ReservationService;
import org.hotel.application.RoomService;
import org.hotel.domain.Accommodation;
import org.hotel.domain.Address;
import org.hotel.domain.Client;
import org.hotel.domain.Damage;
import org.hotel.domain.Price;
import org.hotel.domain.Reservation;
import org.hotel.domain.ReservationState;
import org.hotel.domain.Room;
import org.hotel.domain.RoomState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UrlPathHelper;

/**
 * Klasa jest klasa kontrolera. Odpowiada za kontakt z baza danych oraz przelaczanie widokow
 * @author Konrad 
 */ // skrót alt+shift+j
@Controller
public class HotelController {

	@Autowired
	private ReservationService reservationService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private PriceService priceService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private AccommodationService accommodationService;
	@Autowired
	private DamageService damageService;
	
	@Autowired
    private SimpleMailMessage mailMessage;
	
    @Autowired
    private JavaMailSender mailSender;
    
    
	private Calendar visitingStart;
	private int peopleNumber;
	private int visitLength;
	private String side;
	private double price = 0;
	private Calendar visitingEnd = new GregorianCalendar();
	


	/**
	 * przelacza widok na dane personalne
	 * @param request
	 * @return lancuch znakow, identyfikujacy plik JSP z formularzem danych personalnych
	 */
	@RequestMapping(value = "personal_details", method = RequestMethod.POST)
	public String personalDetailsRedirect(HttpServletRequest request) {
		String[] date1 = request.getParameter("startVisitingDate").split("[\\-.]");
		date1[1] = new Integer(Integer.parseInt(date1[1]) - 1).toString();
		if (date1.length < 3)
			return "reservation";
		visitingStart = new GregorianCalendar(Integer.parseInt(date1[0]), Integer.parseInt(date1[1]),
				Integer.parseInt(date1[2]));
		GregorianCalendar g = new GregorianCalendar();
		g.add(Calendar.DAY_OF_MONTH, -1);
		if (visitingStart.before(g)) {
			request.setAttribute("beforeToday", "beforeToday");
			return "reservation_form";
		}
		peopleNumber = Integer.parseInt(request.getParameter("peopleNumber"));
		visitLength = Integer.parseInt(request.getParameter("visitLength"));
		side = request.getParameter("side");
		List<Price> listPrices = new ArrayList<Price>();
		if(priceService!=null){
			listPrices = priceService.listPrices();
		}
		 
		visitingEnd = (Calendar) visitingStart.clone();
		visitingEnd.add(Calendar.DAY_OF_MONTH, visitLength);
		if(reservationService!=null){
		if (busy()) {
			request.setAttribute("busy", "busy");
			return "reservation_form";
		}
		}
		price = price(visitingStart, visitingEnd, listPrices) * peopleNumber;
		request.setAttribute("price", price);

		return "personal_details";
	}
	
	/**
	 * przełącza widok na formularz rezerwacji
	 * @return lancuch znakow, identyfikujacy plik JSP z formularzem rezerwacji
	 */
	@RequestMapping(value = "reservation_form", method = RequestMethod.GET)
	public String reservationFormRedirect() {
		return "reservation_form";
	}

	/**
	 * przekierowuje na strone glowna
	 * @return lancuch znakow, identyfikujacy plik JSP ze strona glowna
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String indexRedirect() {
		return "index";
	}

	/**
	 * przekierowuje na strone z lista rezerwacji
	 * @param request
	 * @return lancuch znakow, identyfikujacy plik JSP z lista rezerwacji
	 */
	@RequestMapping(value = { "*reservations" }, method = RequestMethod.GET)
	public String reservationsRedirect(HttpServletRequest request) {
		List<Reservation> reservations = getReservations("twoMonths");
		request.setAttribute("reservationsList", reservations);
		return "reservations";
	}

	/**
	 * dokonuje zapisu rezerwacji w bazie danych, przelacza na kolejna strone
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "book", method = RequestMethod.POST)
	public String book(HttpServletRequest request) {
		String[] date = request.getParameter("dateOfBirth").split("[\\-.]");
		date[1] = new Integer(Integer.parseInt(date[1]) - 1).toString();
		Calendar dateOfBirth = new GregorianCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
				Integer.parseInt(date[2]));
		dateOfBirth.add(Calendar.YEAR, 18);
		if (dateOfBirth.after(new GregorianCalendar())) {
			request.setAttribute("price", price);
			request.setAttribute("toYoung", "toYoung");
			return "personal_details";
		}
		Client c = clientService.getClientByEmail(request.getParameter("email"));
		if (c == null) {
			createClient(request.getParameter("firstName"), request.getParameter("surname"),
					request.getParameter("email"), request.getParameter("phoneNumber"),
					new Address(request.getParameter("streetAndNumber"), request.getParameter("city"),
							request.getParameter("zipCode")),
					new GregorianCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
							Integer.parseInt(date[2])));
			c = clientService.getClientByEmail(request.getParameter("email"));

		} else if (c.getName() == request.getParameter("firstName") && c.getSurname() == request.getParameter("surname")
				&& c.getPhoneNumber() == request.getParameter("phoneNumber")
				&& c.getAddress().getStreetAndNumber() == request.getParameter("streetAndNumber")
				&& c.getAddress().getCity() == request.getParameter("city")
				&& c.getAddress().getZipCode() == request.getParameter("zipCode")
				&& c.getDateOfBirth() == new GregorianCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
						Integer.parseInt(date[2]))) {
		} else {
			c.setName(request.getParameter("firstName"));
			c.setSurname(request.getParameter("surname"));
			c.setPhoneNumber(request.getParameter("phoneNumber"));
			c.setAddress(new Address(request.getParameter("streetAndNumber"), request.getParameter("city"),
					request.getParameter("zipCode")));
			c.setDateOfBirth(new GregorianCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
					Integer.parseInt(date[2])));
			clientService.updateClient(c);
		}
		System.out.println(visitingStart);
		System.out.println(visitLength);
		int nr = createReservation(c.getEmail());
		send(c.getEmail(),nr);
		return "reserved";
	}

	/**
	 * wyszukuje rezerwacje wg numeru lub nazwiska
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String search(HttpServletRequest request) {
		String nrOrSurname = request.getParameter("nrOrSurname");
		List<Reservation> reservations = getReservations(nrOrSurname);
		request.setAttribute("reservationsList", reservations);
		return "reservations";
	}

	/**
	 * dokonuje zakwaterowania
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "accommodate*", method = RequestMethod.GET)
	public String accommodateRedirect(HttpServletRequest request) {
		String[] s = (new UrlPathHelper().getPathWithinApplication(request)).split("_|/");
		int resNr = Integer.parseInt(s[2]);
		int roomNr = Integer.parseInt(s[4]);
		createAccommodation(resNr, roomNr);
		List<Reservation> reservations = getReservations("twoMonths");
		request.setAttribute("reservationsList", reservations);
		return "reservations";
	}

	/**
	 * przelacza widok na strone ze szczegolami rezerwacji
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "reservation_details*", method = RequestMethod.GET)
	public String reservationDetailsRedirect(HttpServletRequest request) {
		String [] s = (new UrlPathHelper().getPathWithinApplication(request)).split("_");
		String nr = s[2];
		Reservation r = null;
		if (s.length >3) {
			r = reservationService.getReservationByID(Integer.parseInt(nr));
			r.setConfirmed(true);
			reservationService.updateReservation(r);
		} else {
			r = reservationService.getReservationByID(Integer.parseInt(nr));
		}
		request.setAttribute("reservation", r);
		if (accommodated(r.getReservationId())) {
			request.setAttribute("accommodated", true);
			request.setAttribute("accommodationState", accommodationState(r.getReservationId()));
			request.setAttribute("damage", damage(r.getReservationId()));
		} else
			request.setAttribute("accommodated", false);
		return "reservation_details";
	}

	/**
	 * przelacza na widok z wyborem pokoju dla rezerwacji
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "choose_room*", method = RequestMethod.GET)
	public String chooseRoomRedirect(HttpServletRequest request) {
		String s = (new UrlPathHelper().getPathWithinApplication(request));
		String nr = s.substring(12);
		Reservation reservation = reservationService.getReservationByID(Integer.parseInt(nr));
		GregorianCalendar gc = new GregorianCalendar();
		gc.clear(Calendar.HOUR_OF_DAY);
		gc.clear(Calendar.HOUR);
		gc.clear(Calendar.AM_PM);
		gc.clear(Calendar.MINUTE);
		gc.clear(Calendar.SECOND);
		gc.clear(Calendar.MILLISECOND);
		if (reservation.getStartVisitingDate().after(gc)) {
			request.setAttribute("toEarly", true);
			request.setAttribute("reservation", reservation);
			return "reservation_details";
		}
		if (reservation.getStartVisitingDate().before(gc)) {
			request.setAttribute("toLate", true);
			request.setAttribute("reservation", reservation);
			return "reservation_details";
		}
		List<Room> rooms = roomService.listRooms();
		List<Room> suitableRooms = new ArrayList<>();
		for (Room r : rooms) {
			if (r.getRoomState() == RoomState.CLEANED && r.getMaxPeople() == reservation.getPeopleNumber()
					&& (r.getView().equals(reservation.getRoomSide()) || reservation.getRoomSide().equals("any"))) {
				suitableRooms.add(r);
			}
		}
		request.setAttribute("reservation", reservation);
		request.setAttribute("suitableRooms", suitableRooms);
		return "choose_room";
	}

	/**
	 * wykwaterowuje klienta
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "evict*", method = RequestMethod.GET)
	public String evictRedirect(HttpServletRequest request) {
		String [] s = (new UrlPathHelper().getPathWithinApplication(request)).split("_");
		String nr = s[1];
		double damage = 0;
		if(s.length>2){
			damage = Double.parseDouble(s[2]);
			Reservation reservation = reservationService.getReservationByID(Integer.parseInt(nr));
			request.setAttribute("reservation", reservation);
			request.setAttribute("damage", damage);
			return "damage";
		}
		Accommodation a = accommodationService.getAccommodationByReservationId(Integer.parseInt(nr));
		a.setReservationState(ReservationState.EVICTED);
		Room r = a.getRoom();
		r.setRoomState(RoomState.FORCLEANING);
		accommodationService.updateAccommodation(a);
		roomService.updateRoom(r);
		List<Reservation> reservations = getReservations("twoMonths");
		request.setAttribute("reservationsList", reservations);
		return "reservations";
	}

	/**
	 * zapisuje uszkodzenia w bazie
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "damage*", method = RequestMethod.GET)
	public String damage(HttpServletRequest request) {
		String [] s = (new UrlPathHelper().getPathWithinApplication(request)).split("_");
		String nr = s[1];
		double damage = Double.parseDouble(s[2]);
		Reservation reservation = reservationService.getReservationByID(Integer.parseInt(nr));
		request.setAttribute("reservation", reservation);
		request.setAttribute("damage", damage);
		boolean clientPay = s[3].equals("true") ? true : false;
		createDamage(reservation, damage, clientPay);
		Accommodation a = accommodationService.getAccommodationByReservationId(Integer.parseInt(nr));
		a.setReservationState(ReservationState.EVICTED);
		Room r = a.getRoom();
		r.setRoomState(RoomState.FORCLEANING);
		accommodationService.updateAccommodation(a);
		roomService.updateRoom(r);
		List<Reservation> reservations = getReservations("twoMonths");
		request.setAttribute("reservationsList", reservations);
		return "reservations";
	}
	
	/**
	 * wyswietla mozliwosc przedluzenia pobytu
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "prelong*", method = RequestMethod.GET)
	public String prelong(HttpServletRequest request) {
		String [] s = (new UrlPathHelper().getPathWithinApplication(request)).split("_");
		String nr = s[1];
		Reservation reservation = reservationService.getReservationByID(Integer.parseInt(nr));
		Calendar visitingEnd = (Calendar)reservation.getStartVisitingDate().clone();
		visitingEnd.add(Calendar.DAY_OF_MONTH, reservation.getVisitLength());
		request.setAttribute("reservation", reservation);
		request.setAttribute("visitingEnd", visitingEnd);
		return "prelong";
	}
	
	/**
	 * przedluza pobyt
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "accept*", method = RequestMethod.GET)
	public String acceptPrelong(HttpServletRequest request) {
		String [] s = (new UrlPathHelper().getPathWithinApplication(request)).split("_|-");
		String nr = s[1];
		Reservation reservation = reservationService.getReservationByID(Integer.parseInt(nr));
		Calendar v = (Calendar)reservation.getStartVisitingDate().clone();
		visitingStart = (Calendar)v.clone();
		visitingStart.add(Calendar.DAY_OF_MONTH,reservation.getVisitLength()+1);
		visitingEnd.set(Integer.parseInt(s[2]), Integer.parseInt(s[3])-1, Integer.parseInt(s[4]));
		peopleNumber = reservation.getPeopleNumber();
		visitLength = daysBetween(visitingStart, visitingEnd);
		side = reservation.getRoomSide();
		if(busy()){
			request.setAttribute("busy", true);
		}
		else{
			reservation.setVisitLength(daysBetween(v, visitingEnd)-1);
			reservationService.updateReservation(reservation);
			request.setAttribute("busy", false);
		}
		List<Reservation> reservations = getReservations("twoMonths");
		request.setAttribute("reservationsList", reservations);
		return "reservations";
	}
	
	/**
	 * tworzy klienta i zapisuje go w bazie danych
	 * @param name imie
	 * @param surname nazwisko
	 * @param email email
	 * @param phoneNumber numer telefonu
	 * @param address adres
	 * @param calendar kalendarz
	 */
	public void createClient(String name, String surname, String email, String phoneNumber, Address address,
			Calendar calendar) {
		Client c = new Client();
		c.setName(name);
		c.setSurname(surname);
		c.setEmail(email);
		c.setAddress(address);
		c.setDateOfBirth(calendar);
		c.setPhoneNumber(phoneNumber);
		clientService.addClient(c);
	}

	/**
	 * tworzy rezerwacje
	 * @param email
	 */
	private int createReservation(String email) {
		Reservation r = new Reservation();
		r.setPeopleNumber(peopleNumber);
		r.setFillFormDate(new GregorianCalendar());
		r.setStartVisitingDate(visitingStart);
		r.setVisitLength(visitLength);
		r.setRoomSide(side);
		r.setClient(clientService.getClientByEmail(email));
		r.setConfirmed(false);
		reservationService.addReservation(r);
		return r.getReservationId();
	}

	private double price(Calendar visitingStart, Calendar visitingEnd, List<Price> listPrices) {
		int price = 0;
		int daysDifference = 0;
		for (int i = 0; i < listPrices.size(); i++) {
			if (visitingStart.after(listPrices.get(i).getBeginDate())
					|| visitingStart.getTime() == listPrices.get(i).getBeginDate().getTime()) {
				if (visitingEnd.before(listPrices.get(i).getEndDate())
						|| visitingEnd.getTime() == listPrices.get(i).getEndDate().getTime()) {
					price += daysBetween(visitingStart, visitingEnd) * listPrices.get(i).getPrice();
					return price;
				} else {
					daysDifference = daysBetween(visitingStart, listPrices.get(i).getEndDate());
					visitingStart.add(Calendar.DAY_OF_MONTH, daysDifference);
					if (daysDifference > 0)
						price += daysDifference * listPrices.get(i).getPrice()
								+ price(visitingStart, visitingEnd, listPrices);
					daysDifference = 0;
				}
			} else if (visitingEnd.before(listPrices.get(i).getEndDate())
					|| visitingEnd.getTime() == listPrices.get(i).getEndDate().getTime()) {
				price += daysBetween(visitingStart, visitingEnd) * listPrices.get(i).getPrice();
				return price;
			}
		}
		return price;
	}

	public int daysBetween(Calendar startDate, Calendar endDate) {
		Calendar date = (Calendar) startDate.clone();
		int daysBetween = 0;
		while (date.before(endDate)) {
			date.add(Calendar.DAY_OF_MONTH, 1);
			daysBetween++;
		}
		return daysBetween;
	}

	private boolean busy() {
		int seaRooms = roomsNumberBySide("sea");
		int parkRooms = roomsNumberBySide("park");
		int seaReservation = reservationsNumberBySide("sea");
		int parkReservation = reservationsNumberBySide("park");
		int anyReservation = reservationsNumberBySide("any");

		int freeRooms = 0;

		if (side.equals("sea")) {
			freeRooms = seaRooms - seaReservation;
			int parkFreeRooms = parkRooms - parkReservation;
			anyReservation -= parkFreeRooms;
			freeRooms = anyReservation > 0 ? freeRooms - anyReservation : freeRooms;
		} else if (side.equals("park")) {
			freeRooms = parkRooms - parkReservation;
			int seaFreeRooms = seaRooms - seaReservation;
			anyReservation -= seaFreeRooms;
			freeRooms = anyReservation > 0 ? freeRooms - anyReservation : freeRooms;
		} else {
			freeRooms = seaRooms + parkRooms - seaReservation - parkReservation - anyReservation;
		}

		return freeRooms <= 0;
	}

	private int roomsNumberBySide(String s) {
		
		List<Room> roomsList = new ArrayList<>();
		if(roomService!=null){
			roomsList = roomService.listRooms();
		}
		int rooms = 0;
		for (Room l : roomsList) {
			rooms = l.getMaxPeople() == peopleNumber && s.equals(l.getView()) ? rooms + 1 : rooms;
		}
		return rooms;
	}

	private int reservationsNumberBySide(String s) {
		int reservations = 0;
		List<Reservation> reservationsList = reservationService.listReservations();

		for (Reservation r : reservationsList) {
			if (r.getPeopleNumber() == peopleNumber && r.getRoomSide().equals(s)) {
				boolean duringVisiting = true;
				Calendar start = (Calendar) visitingStart.clone();
				Calendar end = (Calendar) start.clone();
				end.add(Calendar.DAY_OF_MONTH, visitLength);
				Calendar rStart = r.getStartVisitingDate();
				Calendar rEnd = (Calendar) r.getStartVisitingDate().clone();
				rEnd.add(Calendar.DAY_OF_MONTH, r.getVisitLength());
				if (end.before(rStart) || end.equals(rStart) || start.after(rEnd) || start.equals(rEnd)) {
					duringVisiting = false;
				}
				reservations = duringVisiting ? reservations + 1 : reservations;
			}
		}
		return reservations;
	}

	private List<Reservation> getReservations(String s) {
		List<Reservation> reservations = reservationService.listReservations();
		List<Reservation> chosenReservation = new ArrayList<>();
		if (s.equals("allall")) {
			return reservations;
		} else if (s.equals("twoMonths")) {
			for (Reservation r : reservations) {
				Calendar c = (Calendar) r.getStartVisitingDate().clone();
				c.add(Calendar.DAY_OF_MONTH, r.getVisitLength());
				c.add(Calendar.MONTH, 2);
				if (c.after(new GregorianCalendar())) {
					chosenReservation.add(r);
				}
			}
		} else if (s.equals("oneYear")) {
			for (Reservation r : reservations) {
				Calendar c = (Calendar) r.getStartVisitingDate().clone();
				c.add(Calendar.DAY_OF_MONTH, r.getVisitLength());
				c.add(Calendar.MONTH, 12);
				if (c.after(new GregorianCalendar())) {
					chosenReservation.add(r);
				}
			}
		} else {
			for (Reservation r : reservations) {
				if (s.equals((new Integer(r.getReservationId())).toString())
						|| s.toUpperCase().equals(r.getClient().getSurname().toUpperCase())) {
					chosenReservation.add(r);
				}
			}
		}
		return chosenReservation;
	}

	private boolean accommodated(int reservationId) {
		List<Accommodation> l = accommodationService.listAccommodations();
		boolean accommodated = false;
		for (Accommodation a : l) {
			if (a.getReservation().getReservationId() == reservationId) {
				accommodated = true;
			}
		}
		return accommodated;
	}

	private void createAccommodation(int reservationId, int roomNr) {
		Accommodation a = new Accommodation();
		a.setReservation(reservationService.getReservationByID(reservationId));
		Room r = roomService.getRoomByRoomNumber(roomNr);
		r.setRoomState(RoomState.BUSY);
		roomService.updateRoom(r);
		a.setRoom(r);
		a.setReservationState(ReservationState.ACCOMMODATED);
		accommodationService.addAccommodation(a);
	}

	private String accommodationState(int reservationId) {
		List<Accommodation> l = accommodationService.listAccommodations();
		String accommodated = "";
		for (Accommodation a : l) {
			if (a.getReservation().getReservationId() == reservationId) {
				accommodated = a.getReservationState().name();
			}
		}
		return accommodated;
	}

	private void createDamage(Reservation r,double value,boolean clientPay){
		Damage d = new Damage();
		d.setReservation(r);
		d.setValue(value);
		d.setClientPay(clientPay);
		damageService.addDamage(d);
	}
	 
	
	public Damage damage(int reservationId){
		List<Damage> list =damageService.listDamages();
		Damage damage = null;
		for(Damage d : list){
			if(d.getReservation().getReservationId()==reservationId){
				damage=d;
			}
		}
		return damage;
		
	}
	
	private void send(String email, int nr){
        SimpleMailMessage msg = new SimpleMailMessage(this.mailMessage);
        msg.setTo(email);
        msg.setText("Prośba o potwierdzenie rezerwacji\n http://localhost:8080/hotel-webapp/confirm_" + nr);
        mailSender.send(msg);
    }
	
	/**
	 * przelacza widok na strone glowna
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	 public String displayMain(Model model) {
	 return "index";	
	 }
	
	@RequestMapping(value = "confirm*", method = RequestMethod.GET)
	 public String confirm(HttpServletRequest request) {
		String [] s = (new UrlPathHelper().getPathWithinApplication(request)).split("_");
		String nr = s[1];
		Reservation reservation = reservationService.getReservationByID(Integer.parseInt(nr));
		reservation.setConfirmed(true);
		reservationService.updateReservation(reservation);
	 return "confirm";	
	 }
}