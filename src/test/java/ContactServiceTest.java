import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class ContactServiceTest {
	ContactService contacts = new ContactService();
	Contact testContact = new Contact("1","fn","ln","ph","ad");
	@Test
	public void testAddContact() {
		contacts.addContact(testContact);
		assertTrue(contacts.contactsList.keySet().contains("1"));
	}
	@Test
	public void testUpdateContact() {
		contacts.updateContact(testContact, "1", "new fn");
		assertEquals(contacts.getContact("1").firstName, "new fn");
		contacts.updateContact(testContact, "2", "new ln");
		assertEquals(contacts.getContact("1").lastName, "new ln");
		contacts.updateContact(testContact, "3", "new ph");
		assertEquals(contacts.getContact("1").phone, "new ph");
		contacts.updateContact(testContact, "4", "new address");
		assertEquals(contacts.getContact("1").address, "new address");
	}
	@Test
	public void testRemoveContact() {
		contacts.removeContact(testContact);
		assertFalse(contacts.contactsList.keySet().contains("1"));
	}
	
}