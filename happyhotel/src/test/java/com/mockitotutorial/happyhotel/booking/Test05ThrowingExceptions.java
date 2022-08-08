package com.mockitotutorial.happyhotel.booking;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

public class Test05ThrowingExceptions {
	
	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOMock;
	private MailSender mailSenderMock;
	
	@BeforeEach
	public void setup() {
		paymentServiceMock = mock(PaymentService.class);
		roomServiceMock = mock(RoomService.class);
		bookingDAOMock = mock(BookingDAO.class);
		mailSenderMock = mock(MailSender.class);
		
		bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
		
	}
	


	@Test
	public void test_ThrowExceptionWhenNoRoomAvailable() throws BusinessException {
		//given (arrange)
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01),
				LocalDate.of(2020, 01, 05), 2, false);
		
		when(roomServiceMock.findAvailableRoomId(bookingRequest)).thenThrow(BusinessException.class);
	
		
		//when (act)
		Executable executable = () -> bookingService.makeBooking(bookingRequest);

		//then (assert)
		assertThrows(BusinessException.class, executable);
	
		
	}

}
