import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ContactTest {
	@Test
	public void testContactCreation() {
		ContactService contacts = new ContactService();
		//Verify contact ID cannot be null or more than 10 characters
		contacts.addContact(new Contact(null, "fn", "ln", "ph", "ad"));
		contacts.addContact(new Contact("12345678901", "fn", "ln", "ph", "ad"));
		assertEquals(false, contacts.contactsList.keySet().contains(null));
		assertEquals(false, contacts.contactsList.keySet().contains("12345678901"));
		//Verify first name can not be null or more than 10 characters
		contacts.addContact(new Contact("100", null, "ln", "ph", "ad"));
		contacts.addContact(new Contact("101", "12345678901", "ln", "ph", "ad"));
		assertEquals(false, contacts.contactsList.keySet().contains("100"));
		assertEquals(false, contacts.contactsList.keySet().contains("101"));
		//Verify last name can not be null or more than 10 characters
		contacts.addContact(new Contact("100", "fn", null, "ph", "ad"));
		contacts.addContact(new Contact("101", "fn", "12345678901", "ph", "ad"));
		assertEquals(false, contacts.contactsList.keySet().contains("100"));
		assertEquals(false, contacts.contactsList.keySet().contains("101"));
		//Verify phone can not be null or more than 10 characters
		contacts.addContact(new Contact("100", "fn", "ln", null, "ad"));
		contacts.addContact(new Contact("101", "fn", "ln", "12345678901", "ad"));
		assertEquals(false, contacts.contactsList.keySet().contains("100"));
		assertEquals(false, contacts.contactsList.keySet().contains("101"));
		//Verify address can not be null or more than 30 characters
		contacts.addContact(new Contact("100", "fn", "ln", "ph", null));
		contacts.addContact(new Contact("101", "fn", "ln", "ph", "1234567890123456789012345678901"));
		assertEquals(false, contacts.contactsList.keySet().contains("100"));
		assertEquals(false, contacts.contactsList.keySet().contains("101"));
	}
	
	
	
}