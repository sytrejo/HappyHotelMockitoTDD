package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

class Test02DefaultReturnValues {
	
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
	
		System.out.println("List returned " + roomServiceMock.getAvailableRooms());
		System.out.println("Object returned " + roomServiceMock.findAvailableRoomId(null));
		System.out.println("Primitive returned " + roomServiceMock.getRoomCount());
	}

	@Test
	void test_shouldCountAvailablePlaces() {
		
		//given (arrange)
		int expected = 0;
		
		//when (act)
		int actual = bookingService.getAvailablePlaceCount();
		
		//then
		assertEquals(expected, actual);
		
	}

}
