import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task
{
	public String title;
	public String description;

	public Task(String title, String description) {
		this.title=title;
		this.description=description;
	}
}

class TaskManager
{
	public List<Task> tasks;

	public TaskManager()
	{
		tasks = new ArrayList<>();
	}

	public void createTask(Task task) {
		tasks.add(task);
		System.out.printf("Task Added - Task Title : %s - Task Description : %s",task.title,task.description);
	}

	public void searchTask(String viewTitle) {
		for (Task task:tasks) {
			if(task.title.equalsIgnoreCase(viewTitle)) {
				System.out.println("The Task is viewed");
				System.out.printf("Task Title : %s - Task Description : %s",task.title,task.description);
			}
		}
	}

	public void viewTasks() {
		int i=1;
		System.out.println("SNO - Task Title - Task Description");
		for (Task task:tasks) {
			System.out.printf("%d - %s - %s\n",i++,task.title,task.description);
		}
	}
	
	public void removeTask(String title){
        boolean removed = false;
        for (Task task : tasks) {
            if (task.title.equalsIgnoreCase(title)) {
                tasks.remove(task);
                System.out.printf("Task Removed - Task Title : %s\n", title);
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println("Task with the given title not found.");
        }
    }
}

public class Main
{
	public static void main(String[] args) {

		TaskManager taskman = new TaskManager();
		Scanner sc = new Scanner(System.in);

		while(true)
		{
			System.out.println("\nTask Menu:");
			System.out.println("1. Create Task");
			System.out.println("2. Search Task");
			System.out.println("3. View Tasks");
			System.out.println("4. Remove Task");
			System.out.println("5. Exit");
			System.out.print("Choose an option: ");
			int choice = sc.nextInt();
			sc.nextLine();

			switch(choice)
			{
			case 1:
				System.out.print("Enter the Task Title : ");
				String title = sc.nextLine();
				System.out.print("Enter the Task Description : ");
				String description = sc.nextLine();

				Task task=new Task(title,description);
				taskman.createTask(task);
				break;

			case 2:
				System.out.print("To view the task enter the Task Title : ");
				String viewTitle = sc.nextLine();
				taskman.searchTask(viewTitle);
				break;

			case 3:
				System.out.println("All Tasks are");
				taskman.viewTasks();
				break;

			case 4:
				System.out.println("Enter the task to remove");
				String remTitle = sc.nextLine();
				taskman.removeTask(remTitle);
				break;

			case 5:
				sc.close();
				return;

			default:
				System.out.println("Invalid option. Please try again.");

			}
		}

	}
}
