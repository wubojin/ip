package jade.command;

import jade.exception.JadeException;
import jade.task.TaskManager;
import static jade.ui.Ui.INDENT;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    public ListCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String run() throws JadeException {
        StringBuilder message = new StringBuilder();
        message.append(INDENT).append("Here are the tasks in your list:");
        for (int i = 0; i < taskManager.getTaskCount(); i++) {
            message.append("\n").append(INDENT).append(i + 1).append(". ").append(taskManager.getTask(i));
        }
        return displayMessage(message.toString());
    }
}
