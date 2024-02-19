import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class TaskServiceTest {
	TaskService tasks = new TaskService();
	Task testTask = new Task("1","name","desc");
	@Test
	public void testAddTask() {
		tasks.addTask(testTask);
		assertTrue(tasks.tasksList.keySet().contains("1"));
	}
	@Test
	public void testUpdateTask() {
		tasks.updateTask(testTask, "1", "new name");
		assertEquals(tasks.getTask("1").taskName, "new name");
		tasks.updateTask(testTask, "2", "new desc");
		assertEquals(tasks.getTask("1").taskDesc, "new desc");
	}
	@Test
	public void testRemoveTask() {
		tasks.removeTask(testTask);
		assertFalse(tasks.tasksList.keySet().contains("1"));
	}
	
}