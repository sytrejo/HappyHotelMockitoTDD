package com.mockitotutorial.happyhotel.booking;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import junit.framework.Assert;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

public class Test01FirstMocks {
	
	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOMock;
	private MailSender mailSenderMock;
	
	@BeforeEach
	public void setup() {
		this.paymentServiceMock = mock(PaymentService.class);
		this.roomServiceMock = mock(RoomService.class);
		this.bookingDAOMock = mock(BookingDAO.class);
		this.mailSenderMock = mock(MailSender.class);
		
		this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
		
	}
	


	@Test
	public void test_shouldCalculateCorrectPriceWhenCorrectInput() {
		//given (arrange)
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01),
				LocalDate.of(2020, 01, 05), 2, false);
		
		int expected = 4 * 2 * 50;
		
		//when (act)
		double tempActual = bookingService.calculatePrice(bookingRequest);
		int actual = (int)tempActual;
		
		//then (assert)
		assertEquals(expected, actual);
	
		
	}

}
