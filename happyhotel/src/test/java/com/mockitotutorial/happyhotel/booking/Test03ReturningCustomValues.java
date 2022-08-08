package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Test03ReturningCustomValues {
	
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
	void test_shouldCountAvailablePlacesWhenOneRoomAvailable() {
		
		//given (arrange)
		when(this.roomServiceMock.getAvailableRooms()).thenReturn(Collections.singletonList(new Room("Room 1", 5)));
		
		int expected = 5;
		
		//when (act)
		int actual = bookingService.getAvailablePlaceCount();
		
		//then
		assertEquals(expected, actual);
		
	}
	
	@Test
	void test_shouldCountAvailablePlacesWhenMultipleRoomsAvailable() {
		
		//given (arrange)
		List<Room> rooms = Arrays.asList(new Room ("Room 1", 2), new Room("Room 2", 5));
		when(this.roomServiceMock.getAvailableRooms()).thenReturn(rooms);
		
		int expected = 7;
		
		//when (act)
		int actual = bookingService.getAvailablePlaceCount();
		
		//then
		assertEquals(expected, actual);
		
	}



}
