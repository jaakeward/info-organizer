import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
public class ServiceControllerTest {
	@Test
	public void testTaskService() {
		ServiceController app = new ServiceController("1");
		Task ta = new Task("100", "name", "last");
		app.tasks.addTask(ta);
		assertEquals(app.tasks.getTask("100"), ta);
	}
	@Test
	public void testAppointmentService() {
		ServiceController app = new ServiceController("2");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mma");
		LocalDateTime ldt = LocalDateTime.parse("10/10/2024 10:00AM", dtf);
		Appointment ap = new Appointment("101", ldt, "desc");
		app.appointments.addAppointment(ap);
		assertEquals(app.appointments.getAppointment("101"), ap);
	}
	@Test
	public void testContactService() {
		ServiceController app = new ServiceController("3");
		Contact co = new Contact("103", "name", "last", "ph", "ad");
		app.contacts.addContact(co);
		assertEquals(app.contacts.getContact("103"), co);
	}
}