package org.hotel.webapp;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:test-spring-context.xml" })
@Transactional 
@RunWith(MockitoJUnitRunner.class)
public class AppTest  {

	public AppTest(){
		
	}

	@Mock
	private HttpServletRequest request;
	
    HotelController hotelController= new HotelController();
    

    
    @Test
    public void testVisitingStartDate() throws Exception{
       //given
    	Calendar c = new GregorianCalendar();
    	final String startVisitingDateThatDay = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DAY_OF_MONTH);
    	c.add(Calendar.DAY_OF_MONTH, 1);
    	final String startVisitingDateNextDay = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DAY_OF_MONTH);
    	c.add(Calendar.DAY_OF_MONTH, -2);
    	final String startVisitingDateDayBefore = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DAY_OF_MONTH);
    	
    	given(request.getParameter("peopleNumber")).willReturn("2");
    	given(request.getParameter("visitLength")).willReturn("2");
       final String expectedResult1 = "personal_details";
       final String expectedResult2 = "reservation_form";
       String actualResult="";
       
       given(request.getParameter("startVisitingDate")).willReturn(startVisitingDateThatDay);
       actualResult = hotelController.personalDetailsRedirect(request);
       assertEquals(expectedResult1, actualResult);
      
       given(request.getParameter("startVisitingDate")).willReturn(startVisitingDateNextDay);
       actualResult = hotelController.personalDetailsRedirect(request);
       assertEquals(expectedResult1, actualResult);
       
       given(request.getParameter("startVisitingDate")).willReturn(startVisitingDateDayBefore);
       actualResult = hotelController.personalDetailsRedirect(request);
       assertEquals(expectedResult2, actualResult);
    }
    
 	
    @Test
    public void dateOfBirthCheck() throws Exception{
       //given
       given(request.getParameter("dateOfBirth")).willReturn("2015-02-02");
       final String expectedResult = "personalDetails";

       //when
       final String actualResult = hotelController.book(request);

       //then
       assertEquals(expectedResult, actualResult);
    }
   
}