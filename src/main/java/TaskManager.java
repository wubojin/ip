import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTask(int index, boolean isDone) {
        if (isValidTaskIndex(index)) {
            if (isDone) {
                tasks.get(index).markAsDone();
            } else {
                tasks.get(index).markAsNotDone();
            }
        }
    }

    public void deleteTask(int index) throws JadeException {
        if (isValidTaskIndex(index)) {
            tasks.remove(index);
        } else {
            throw new JadeException("Hmm, no such task. Try again.");
        }
    }

    public boolean isValidTaskIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    public int getTaskCount() {
        return tasks.size();
    }
}