import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
	@Test
	public void testTaskCreation() {
		TaskService tasks = new TaskService();
		//Verify task ID cannot be null or more than 10 characters
		tasks.addTask(new Task(null, "name", "desc"));
		tasks.addTask(new Task("12345678901", "name", "desc"));
		assertEquals(false, tasks.tasksList.keySet().contains(null));
		assertEquals(false, tasks.tasksList.keySet().contains("12345678901"));
		//Verify name can not be null or more than 20 characters
		tasks.addTask(new Task("100", null, "desc"));
		tasks.addTask(new Task("101", "123456789012345678901", "desc"));
		assertEquals(false, tasks.tasksList.keySet().contains("100"));
		assertEquals(false, tasks.tasksList.keySet().contains("101"));
		//Verify description can not be null or more than 50 characters
		String longDesc = "x".repeat(51);
		tasks.addTask(new Task("100", "name", null));
		tasks.addTask(new Task("101", "name", longDesc));
		assertEquals(false, tasks.tasksList.keySet().contains("100"));
		assertEquals(false, tasks.tasksList.keySet().contains("101"));
	}
	
	
	
}