import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class ServiceController {
	public FileController serviceFiles;
    public AppointmentService appointments;
	public ContactService contacts;
	public TaskService tasks;
	Scanner input = new Scanner(System.in);
	public File saveDir = new File(".\\src\\main\\resources");
	public static void displayControllerMenu() {
    	 System.out.println("Enter choice: \n" +
                 "1: Tasks\n" + 
                 "2: Appointments\n" +
                 "3: Contacts\n" +
                 "9: Exit");
    }
    public ServiceController(String choice) {
    	initFiles();
    	switch (choice) {
    	case "1":
    		tasks = new TaskService();
    		tasks.runService();
    		break;
    	case "2":
    		appointments = new AppointmentService();
    		appointments.runService();
    		break;
    	case "3":
    		contacts = new ContactService();
    		contacts.runService();
    		break;
    	}
    }
    public ServiceController() {
    	initFiles();
        String choice = "";
        while (!choice.equals("9")){
        	displayControllerMenu();
        	choice = input.nextLine();
        	switch (choice) {
        	case "1":
        		tasks = new TaskService();
        		tasks.runService();
        		break;
        	case "2":
        		appointments = new AppointmentService();
        		appointments.runService();
        		break;
        	case "3":
        		contacts = new ContactService();
        		contacts.runService();
        		break;
        	}
        }
    }
    public void initFiles() {
    	String[] files = {"contacts.csv", "appointments.csv", "tasks.csv"};
    	if (!saveDir.exists()) {
    		saveDir.mkdir();
    	}
		for (String s : files) {
			File f = new File(saveDir + "\\" + s);
			if (!f.exists()) {
				try {
					f.createNewFile();
					System.out.println("Created " + f.getCanonicalPath());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
    }
}
