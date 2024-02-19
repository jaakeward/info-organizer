import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


public class AppointmentServiceTest {
	/*Create Appointment Service, format the date formatter, and set up some dummy Appointments */
	AppointmentService apps = new AppointmentService();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mma");
	Appointment testAppFuture = new Appointment("1",LocalDateTime.parse("10/10/2024 10:00AM", dtf),"desc");
	Appointment testAppPast = new Appointment("2",LocalDateTime.parse("10/10/2000 10:00AM", dtf),"desc");
	@Test
	public void testAddAppointment() {
		//Add dummy Appointments to the Appointment Service
		apps.addAppointment(testAppFuture);
		apps.addAppointment(testAppPast);
		//Expect one Appointment in the Appointment Service, one rejected
		assertTrue(apps.appsList.keySet().contains("1"));
		assertFalse(apps.appsList.keySet().contains("2"));
	}
	@Test
	public void testUpdateAppointment() {
		//Attempt to update with valid info and verify the update was made
		apps.updateAppointment(testAppFuture, "1", "10/11/2024 11:00AM");
		assertEquals(apps.getAppointment("1").appDate, LocalDateTime.parse("10/11/2024 11:00AM", dtf));
		apps.updateAppointment(testAppFuture, "2", "new desc");
		assertEquals(apps.getAppointment("1").appDesc, "new desc");
		//Attempt to update with invalid info and verify the update was rejected
		apps.updateAppointment(testAppFuture, "1", "10/11/2023 11:00AM");
		assertNotEquals(apps.getAppointment("1").appDate, LocalDateTime.parse("10/11/2023 11:00AM", dtf));
		String longDesc = "*".repeat(51);
		apps.updateAppointment(testAppFuture, "2", longDesc);
		assertNotEquals(apps.getAppointment("1").appDesc, longDesc);
	}
	@Test
	public void testRemoveAppointment() {
		apps.removeAppointment(testAppFuture);
		assertFalse(apps.appsList.keySet().contains("1"));
	}
	
}