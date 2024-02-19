import java.util.HashMap;
import java.util.Scanner;

public class ContactService {
    public HashMap<String, Contact> contactsList;
    Scanner input = new Scanner(System.in);
    String saveDest = ".\\src\\main\\resources\\contacts.csv";
    public ContactService() {
        loadContacts();
    }
    public void runService() {
    	Contact current;
        String choice = "";
        String id;
        while (!choice.equals("9")){
            displayMenu();
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    addContact();
                    break;
                case "2":
                    System.out.println("Enter contact ID: ");
                    id = input.nextLine();
                    current = getContact(id);
                    removeContact(current);
                    break;
                case "3":
                    System.out.println("Enter contact ID: ");
                    id = input.nextLine();
                    if (getContact(id) != null){
                       current = getContact(id);
                        displayContact(current);
                        System.out.println("Enter update field (1-4): ");
                        String field = input.nextLine();
                        while (Integer.parseInt(field) > 4 || Integer.parseInt(field) < 1){
                            System.out.println("Please enter valid option (1-4): ");
                            field = input.nextLine();
                        }
                        System.out.println("Enter new info: ");
                        String newInfo = input.nextLine();
                        current = updateContact(current, field, newInfo); 
                    }
                    else {
                        System.out.println("Invalid ID");
                    }
                    break;
                case "4":
                    System.out.println("Enter contact ID: ");
                    id = input.nextLine();
                    current = getContact(id);
                    displayContact(current);
                    break;
                case "9":
                    break;
            }
        }
        saveContacts();
        System.out.println("Thanks for using the Contacts Service.");
    }
    public Contact getContact(String id) {
        try {
            return contactsList.get(id);
        }
        catch (NullPointerException e){
            return null;
        }
    }
    public void displayMenu() {
        System.out.println("Enter choice: \n" +
                        "1: Add contact\n" + 
                        "2: Remove contact\n" +
                        "3: Update contact info\n" +
                        "4: View contact info\n" +
                        "9: Exit");
    }
    public void loadContacts() {
    	FileController fctl = new FileController(saveDest);
    	contactsList = fctl.readContactFromFile();
    }
    public void saveContacts() {
    	FileController fctl = new FileController(saveDest);
		fctl.writeContactToFile(contactsList);
    }
    public void addContact() {
        String contactID;
        String[] contactInfo = new String[4];
        System.out.println("Enter contact ID: ");
        contactID = input.nextLine();
        if (!contactsList.keySet().contains(contactID)){
            System.out.println("Enter first name: ");
            contactInfo[0] = input.nextLine();
            System.out.println("Enter last name: ");
            contactInfo[1] = input.nextLine();
            System.out.println("Enter phone number: ");
            contactInfo[2] = input.nextLine();
            System.out.println("Enter address: ");
            contactInfo[3] = input.nextLine();
            if (verifyInput(contactInfo[0], contactInfo[1], contactInfo[2], contactInfo[3])) {
            Contact co = new Contact(contactID, contactInfo[0], contactInfo[1],
                        contactInfo[2], contactInfo[3]);
            contactsList.put(contactID, co);
            }
        }
        else {
            System.out.println("Invalid input");
        }
    }
    public void addContact(Contact co) {
    	try {
    		if (isUnique(co, co.contactID) && verifyInput(co.firstName, co.lastName, co.phone, co.address)) {
    			contactsList.put(co.contactID, co);
    		}
    		else {
    			System.out.println("Invalid Contact Info/ID:" + 
    							"\n-     Contact ID: " + (co.contactID != null ? co.contactID : "Error") +
    							"\n-     Contact Name: " + (co.firstName != null ? co.firstName : "Error") + 
    							(co.lastName != null ? co.lastName : "Error") +
    							"\n-     Contact Phone: " + (co.phone != null ? co.phone : "Error") +
    							"\n-     Contact Address: " + (co.address != null ? co.address : "Error") +
    							"\n");
    		}
    	}catch (NullPointerException e) {
    	}
    }
    public void removeContact(Contact co) {
    	try {
    		contactsList.remove(co.contactID);
    	}catch (NullPointerException e) {		
    	}
    }
    public void displayContact(Contact co){
        try{
            System.out.println("Contact ID: " + co.contactID + 
                "\n1. First Name: " + co.firstName +
                "\n2. Last Name: " + co.lastName +
                "\n3. Phone Number: " + co.phone +
                "\n4. Address: " + co.address + "\n");
        }catch(NullPointerException e){
        } 
    }
    public Contact updateContact(Contact co, String option, String newInfo) {
        try {
            switch (option) {
                case "1":
                    co = new Contact(co.contactID, newInfo, co.lastName,
                                co.phone, co.address);
                    if (verifyInput(newInfo, co.lastName,
                                co.phone, co.address)) {
                    	removeContact(co);
                    	addContact(co);
                    }
                    break;
                case "2": 
                    co = new Contact(co.contactID, co.firstName, newInfo,
                                co.phone, co.address);
                    if (verifyInput(co.firstName, newInfo,
                            co.phone, co.address)) {
	                	removeContact(co);
	                	addContact(co);
	                }
                    break;
                case "3":
                    co = new Contact(co.contactID, co.firstName,
                                co.lastName, newInfo, co.address);
                    if (verifyInput(co.firstName, co.lastName, 
                    		newInfo, co.address)) {
	                	removeContact(co);
	                	addContact(co);
	                }
                    break;
                case "4":
                    co = new Contact(co.contactID, co.firstName,
                                co.lastName, co.phone, newInfo);
                    if (verifyInput(co.firstName, co.lastName, 
                            co.phone, newInfo)) {
	                	removeContact(co);
	                	addContact(co);
	                }
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }catch (NullPointerException e){
        }
        return co;
    }
    private boolean isUnique(Contact co, String cID) {
    	try {
    		if (contactsList.keySet().contains(cID)){
        		co = null;
    			return false;
    		}
    		else if(cID == null) {
    			co = null;
    			return false;
    		}
    		else if(cID.length() > 10) {
    			co = null;
    			return false;
    		}
    		return true;
    	}catch(NullPointerException e) {
    		co = null;
    		return false;
    	} 
    }
    private boolean verifyInput(String fn, String ln, String ph, String ad)
    {
        boolean verified = true;
        if ((fn == null) || (fn.length() > 10)){
            System.out.println("First name error (max 10 characters)");
            verified = false;
        }
        if ((ln == null) || (ln.length() > 10)){
            System.out.println("Last name error (max 10 characters)");
            verified = false;
        }
        if ((ph == null) || (ph.length() > 10)){
            System.out.println("Phone number error (max 10 characters)");
            verified = false;
        }
        if ((ad == null) || (ad.length() > 30)){
            System.out.println("Address error (max 30 characters)");
            verified = false;
        }
        return verified;
    }
}