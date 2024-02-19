import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class AppointmentTest {
	@Test
	public void testAppointmentCreation() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mma");
		LocalDateTime future = LocalDateTime.parse("10/10/2024 10:00AM", dtf);
		LocalDateTime past = LocalDateTime.parse("10/10/2000 10:00AM", dtf);
		
		AppointmentService apps = new AppointmentService();
		//Verify Appointment ID cannot be null or more than 10 characters
		apps.addAppointment(new Appointment(null, future, "desc"));
		apps.addAppointment(new Appointment("12345678901", future, "desc"));
		assertEquals(false, apps.appsList.keySet().contains(null));
		assertEquals(false, apps.appsList.keySet().contains("12345678901"));
		//Verify date can not be null or in the past
		apps.addAppointment(new Appointment("100", null, "desc"));
		apps.addAppointment(new Appointment("101", past, "desc"));
		assertEquals(false, apps.appsList.keySet().contains("100"));
		assertEquals(false, apps.appsList.keySet().contains("101"));
		//Verify description can not be null or more than 50 characters
		String longDesc = "x".repeat(51);
		apps.addAppointment(new Appointment("102", future, null));
		apps.addAppointment(new Appointment("103", future, longDesc));
		assertEquals(false, apps.appsList.keySet().contains("102"));
		assertEquals(false, apps.appsList.keySet().contains("103"));
	}
	
	
	
}