import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class AppointmentService {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mma");
    public HashMap<String, Appointment> appsList;
    String saveDest = ".\\src\\main\\resources\\appointments.csv";
    Scanner input = new Scanner(System.in);
    public AppointmentService() {
        loadAppointments();
    }
    public void runService() {
        Appointment current;
        String choice = "";
        String id;
        while (!choice.equals("9")){
        	displayMenu();
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    addAppointment();
                    break;
                case "2":
                    System.out.println("Enter app ID: ");
                    id = input.nextLine();
                    current = getAppointment(id);
                    removeAppointment(current);
                    break;
                case "3":
                    System.out.println("Enter app ID: ");
                    id = input.nextLine();
                    if (getAppointment(id) != null){
                    	current = getAppointment(id);
                        displayAppointment(current);
                        System.out.println("Enter update field (1-2): ");
                        String field = input.nextLine();
                        System.out.println("Enter new info: ");
                        String newInfo = input.nextLine();
                        current = updateAppointment(current, field, newInfo); 
                    }
                    else {
                        System.out.println("Invalid ID");
                    }
                    break;
                case "4":
                    System.out.println("Enter app ID: ");
                    id = input.nextLine();
                    displayAppointment(getAppointment(id));
                    break;
                case "9":
                    break;
            }
        }
        saveAppointments();
        System.out.println("Thanks for using the Appointment Service.");
    }
    public Appointment getAppointment(String id) {
        try {return appsList.get(id);}
        catch (NullPointerException e){return null;}
    }
    public void displayMenu() {
        System.out.println("Enter choice: \n" +
                        "1: Add Appointment\n" + 
                        "2: Remove Appointment\n" +
                        "3: Update Appointment\n" +
                        "4: View Appointment info\n" +
                        "9: Exit");
    }
    public void loadAppointments() {
    	FileController fctl = new FileController(saveDest);
    	appsList = fctl.readAppointmentFromFile();
    }
    public void saveAppointments() {
    	FileController fctl = new FileController(saveDest);
		fctl.writeAppointmentToFile(appsList);
    }
    public void addAppointment() {
        String appID, appDesc;
        LocalDateTime appDate;
        System.out.println("Enter Appointment ID: ");
        appID = input.nextLine();
        if (isUnique(appID)){
            try{
                System.out.println("Enter Appointment Date: ");
                appDate = LocalDateTime.parse(input.nextLine(), dtf);
                if (appDate.isBefore(LocalDateTime.now())){
                    throw new DateTimeParseException("Date in the past", appDate.toString(), 0);
                }
                System.out.println("Enter Appointment Description: ");
                appDesc = input.nextLine();
                while (appDesc.length() > 50){
                    System.out.println("Enter description less than 50 characters");
                    appDesc = input.nextLine();
                }
                Appointment ap = new Appointment(appID, appDate, appDesc);
                appsList.put(ap.appID, ap);
            }catch (DateTimeParseException dtpe){
                System.out.println("Invalid date - correct format: 12/31/2025 3:00PM. Dates must be in the future.");
            }    
        }
    }
    public void addAppointment(Appointment ap) {
    	try {
    		if (verify(ap) && isUnique(ap.appID)) {
    			appsList.put(ap.appID, ap);
    		}
    		else {
    			System.out.println("Invalid Appointment Info/ID:" + 
    							"\n-     Appointment ID: " + (ap.appID != null ? ap.appID : "Error") +
    							"\n-     Appointment Date: " + (ap.appDate.format(dtf) != null ? ap.appDate.format(dtf) : "Error")+
    							"\n-     Appointment Description: " + (ap.appDesc != null ? ap.appDesc : "Error")+ "\n");
    		}
    	}catch (NullPointerException e) {
            System.out.println("Appointment could not be created");
    	}
    }
    public void removeAppointment(Appointment ap) {
    	try {
    		appsList.remove(ap.appID);
    	}catch (NullPointerException e) {
            System.out.println("Appointment not found");		
    	}
    }
    public void displayAppointment(Appointment ap){
        try{
            System.out.println("\nAppointment ID: " + ap.appID + 
                "\n1. Date: " + ap.appDate.format(dtf) +
                "\n2. Description: " + ap.appDesc + "\n");
        }catch(NullPointerException e){
            System.out.println("Appointment not found");
        } 
    }
    public Appointment updateAppointment(Appointment ap, String option, String newInfo) {
    	switch (option){
            case "1":
                try {
                    ap = new Appointment(ap.appID, LocalDateTime.parse(newInfo, dtf), ap.appDesc);
                }catch (DateTimeParseException e){
                    System.out.println("Invalid date update");
                }
                break;
            case "2":
                ap = new Appointment(ap.appID, ap.appDate, newInfo);
                break;
            default:
            	System.out.println("Invalid option.");
        }
    	if (verify(ap)) {
    		
    		removeAppointment(ap);
    		addAppointment(ap);
    	}
        return ap;
    }
    private boolean isUnique(String aID) {
		if (appsList.keySet().contains(aID)){
			return false;
		}
		else if((aID == null) || (aID.length() > 10)){
			return false;
		}
		return true;
    }
    private boolean verify(Appointment ap) {
    	boolean verified = true;
    	if ((ap.appDate == null)||(ap.appDate.isBefore(LocalDateTime.now()))) {
    		return false;
    	}
    	else if ((ap.appDesc == null)||(ap.appDesc.length() > 50)) {
    		return false;
    	}
    	return verified;
    }
    
    
    
    
    
    
}