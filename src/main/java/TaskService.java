import java.util.HashMap;
import java.util.Scanner;

public class TaskService {
    public HashMap<String, Task> tasksList;
    Scanner input = new Scanner(System.in);
    String saveDest = ".\\src\\main\\resources\\tasks.csv";
    public TaskService() {
        loadTasks();
    }
    public void runService() {
    	Task current;
        String choice = "";
        String id;
        while (!choice.equals("9")){
            displayMenu();
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    addTask();
                    break;
                case "2":
                    System.out.println("Enter task ID: ");
                    id = input.nextLine();
                    current = getTask(id);
                    removeTask(current);
                    break;
                case "3":
                    System.out.println("Enter task ID: ");
                    id = input.nextLine();
                    if (getTask(id) != null){
                       current = getTask(id);
                        displayTask(current);
                        System.out.println("Enter update field (1-2): ");
                        String field = input.nextLine();
                        while (Integer.parseInt(field) > 4 || Integer.parseInt(field) < 1){
                            System.out.println("Please enter valid option (1-2): ");
                            field = input.nextLine();
                        }
                        System.out.println("Enter new info: ");
                        String newInfo = input.nextLine();
                        current = updateTask(current, field, newInfo); 
                    }
                    else {
                        System.out.println("Invalid ID");
                    }
                    break;
                case "4":
                    System.out.println("Enter task ID: ");
                    id = input.nextLine();
                    current = getTask(id);
                    displayTask(current);
                    break;
                case "9":
                    break;
            }
        }
        saveTasks();
        System.out.println("Thanks for using the Tasks Service.");
    }
    public Task getTask(String id) {
        try {
            return tasksList.get(id);
        }
        catch (NullPointerException e){
            return null;
        }
    }
    public void displayMenu() {
        System.out.println("Enter choice: \n" +
                        "1: Add Task\n" + 
                        "2: Remove Task\n" +
                        "3: Update Task info\n" +
                        "4: View Task info\n" +
                        "9: Exit");
    }
    public void loadTasks() {
    	FileController fctl = new FileController(saveDest);
    	tasksList = fctl.readTaskFromFile();
    }
    public void saveTasks() {
    	FileController fctl = new FileController(saveDest);
		fctl.writeTaskToFile(tasksList);
    }
    public void addTask() {
        String taskID;
        String[] taskInfo = new String[2];
        System.out.println("Enter Task ID: ");
        taskID = input.nextLine();
        if (!tasksList.keySet().contains(taskID)){
            System.out.println("Enter task name: ");
            taskInfo[0] = input.nextLine();
            System.out.println("Enter task description: ");
            taskInfo[1] = input.nextLine();
            if (verifyInput(taskInfo[0], taskInfo[1])) {
            Task ta = new Task(taskID, taskInfo[0], taskInfo[1]);
            tasksList.put(taskID, ta);
            }
        }
        else {
            System.out.println("Invalid input");
        }
    }
    public void addTask(Task ta) {
    	try {
    		if (isUnique(ta, ta.taskID) && verifyInput(ta.taskName, ta.taskDesc)) {
    			tasksList.put(ta.taskID, ta);
    		}
    		else {
    			System.out.println("Invalid Task Info/ID:" + 
    							"\n-     Task ID: " + (ta.taskID != null ? ta.taskID : "Error") +
    							"\n-     Task Name: " + (ta.taskName != null ? ta.taskName : "Error") +
    							"\n-     Task Description: " + (ta.taskDesc != null ? ta.taskDesc : "Error") + "\n");
    		}
    	}catch (NullPointerException e) {
    	}
    }
    public void removeTask(Task ta) {
    	try {
    		tasksList.remove(ta.taskID);
    	}catch (NullPointerException e) {		
    	}
    }
    public void displayTask(Task ta){
        try{
            System.out.println("Task ID: " + ta.taskID + 
                "\n1. Name: " + ta.taskName +
                "\n2. Description: " + ta.taskDesc);
        }catch(NullPointerException e){
        } 
    }
    public Task updateTask(Task ta, String option, String newInfo) {
        try {
            switch (option) {
                case "1":
                    ta = new Task(ta.taskID, newInfo, ta.taskDesc);
                    if (verifyInput(newInfo, ta.taskDesc)) {
                    	removeTask(ta);
                    	addTask(ta);
                    }
                    break;
                case "2": 
                    ta = new Task(ta.taskID, ta.taskName, newInfo);
                    if (verifyInput(ta.taskName, newInfo)) {
	                	removeTask(ta);
	                	addTask(ta);
	                }
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }catch (NullPointerException e){
        }
        return ta;
    }
    private boolean isUnique(Task ta, String tID) {
    	try {
    		if (tasksList.keySet().contains(tID)){
    			return false;
    		}
    		else if(tID == null) {
    			return false;
    		}
    		else if(tID.length() > 10) {
    			return false;
    		}
    		return true;
    	}catch(NullPointerException e) {
    		return false;
    	} 
    }
    private boolean verifyInput(String name, String desc)
    {
        boolean verified = true;
        if ((name == null) || (name.length() > 20)){
            System.out.println("Task name error (max 20 characters)");
            verified = false;
        }
        if ((desc == null) || (desc.length() > 50)){
            System.out.println("Task description error (max 50 characters)");
            verified = false;
        }
        return verified;
    }
}