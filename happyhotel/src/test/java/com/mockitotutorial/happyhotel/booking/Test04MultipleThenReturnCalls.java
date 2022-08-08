package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Test04MultipleThenReturnCalls {

	PaymentService paymentServiceMock;
	RoomService roomServiceMock;
	BookingDAO bookingDAOMock;
	MailSender mailSenderMock;
	BookingService bookingService;
	
	@BeforeEach
	public void setup() {
		this.paymentServiceMock = mock(PaymentService.class);
		this.roomServiceMock = mock(RoomService.class);
		this.bookingDAOMock = mock(BookingDAO.class);
		this.mailSenderMock = mock(MailSender.class);
		
		this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
	}

	@Test
	void test_shouldCountAvailablePlacesWhenCalledMultipleTimes() {
		
		//given (arrange)
		when(this.roomServiceMock.getAvailableRooms())
				 .thenReturn(Collections.singletonList(new Room("Room 1", 5)))
				 .thenReturn(Collections.emptyList());
		
		int expectedFirstCall = 5;
		int expectedSecondCall = 0;
		
		//when (act)
		int actualFirst = bookingService.getAvailablePlaceCount();
		int actualSecond = bookingService.getAvailablePlaceCount();
		
		//then
		assertAll(
			() -> assertEquals(expectedFirstCall, actualFirst),
			() -> assertEquals(expectedSecondCall, actualSecond));
		
		
	}
	



}
