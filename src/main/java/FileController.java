import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.time.LocalDateTime;

public class FileController{
	BufferedReader in;
	BufferedWriter out;
	String fn;
	
	
	public FileController(String fileName) {
		fn = fileName;
	}
	public HashMap<String, Task> readTaskFromFile(){
		try{
			in = new BufferedReader(new FileReader(fn));
			HashMap<String, Task> loadedTasks = new HashMap<String, Task>();
			String line = in.readLine();
			String[] lines;
			while (line != null) {
				lines = line.split(",");
				loadedTasks.put(lines[0], new Task(lines[0], lines[1], lines[2]));
				line = in.readLine();
			}
			in.close();
			return loadedTasks;
			}catch(IOException ioe) {
			}
		return new HashMap<String, Task>();
	}
	public void writeTaskToFile(HashMap<String, Task> saveData) {
		try{
			File temp = new File(fn);
			temp.delete();
			temp.createNewFile();
			for (String key : saveData.keySet()) {
				Task ta = saveData.get(key);
				out = new BufferedWriter(new FileWriter(fn, true));
				out.write(ta.taskID + "," + ta.taskName + "," + ta.taskDesc + "\n");
				out.flush();
			} 
		}catch (IOException e) {
			System.out.println("WRITE: " + e.getMessage());
		}
	}
	public HashMap<String, Appointment> readAppointmentFromFile() {
		try {
			in = new BufferedReader(new FileReader(fn));
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mma");
			HashMap<String, Appointment> loadedAppointments = new HashMap<String, Appointment>();
			String line = in.readLine();
			String[] lines;
			while (line != null) {
				lines = line.split(",");
				loadedAppointments.put(lines[0], new Appointment(lines[0], LocalDateTime.parse(lines[1], dtf), lines[2]));
				line = in.readLine();
			}
			in.close();
			return loadedAppointments;
		}catch (IOException e) {	
		}
		return new HashMap<String, Appointment>();
	}
	public void writeAppointmentToFile(HashMap<String, Appointment> saveData) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mma");
		try{
			File temp = new File(fn);
			temp.delete();
			temp.createNewFile();
			for (String key : saveData.keySet()) {
				Appointment ap = saveData.get(key);
				out = new BufferedWriter(new FileWriter(fn, true));
				out.write(ap.appID + "," + ap.appDate.format(dtf) + "," + ap.appDesc + "\n");
				out.flush();
			} 
		}catch (IOException e) {
			System.out.println("WRITE: " + e.getMessage());
		}
	}
	public HashMap<String, Contact> readContactFromFile() {
		try {
			in = new BufferedReader(new FileReader(fn));
			HashMap<String, Contact> loadedContacts = new HashMap<String, Contact>(); 
			String line = in.readLine();
			String[] lines;
			while (line != null) {
				lines = line.split(",");
				loadedContacts.put(lines[0], new Contact(lines[0], lines[1], lines[2], lines[3], lines[4]));
				line = in.readLine();
			}
			in.close();
			return loadedContacts;
		}catch (IOException e) {	
		}
		return new HashMap<String, Contact>();
	}
	public void writeContactToFile(HashMap<String, Contact> saveData) {
		try{
			File temp = new File(fn);
			temp.delete();
			temp.createNewFile();
			for (String key : saveData.keySet()) {
				Contact co = saveData.get(key);
				out = new BufferedWriter(new FileWriter(fn, true));
				out.write(co.contactID + "," + co.firstName + "," + co.lastName + "," + co.phone + "," + co.address + "\n");
				out.flush();
			} 
		}catch (IOException e) {
			System.out.println("WRITE: " + e.getMessage());
		}
	}
}