package jade;

import jade.command.Command;
import jade.exception.JadeException;
import jade.parser.Parser;
import jade.storage.Storage;
import jade.task.TaskManager;
import jade.ui.Ui;

/**
 * The main entry point for the Jade application.
 * Initialises the storage, task manager, and user interface, and starts the application.
 * The application can be run either as a text-based user interface (UI)
 * or integrated into a graphical user interface (GUI).
 */
public class Jade {
    private static boolean isErrorResponse = false;
    private static final String FILE_PATH = "./data/jade.txt";
    private static final Storage STORAGE = new Storage(FILE_PATH);
    private static final TaskManager TASK_MANAGER = new TaskManager(STORAGE);
    private static final Parser PARSER = new Parser(TASK_MANAGER);

    /**
     * Starts the Jade text-based UI application.
     * Initialises the Storage, TaskManager, and UI, and then runs the UI.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        assert PARSER != null : "Parser should be initialised";

        Ui ui = new Ui(PARSER);
        ui.run();
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input The user's input message.
     * @return A string response based on the input command.
     */
    public String getResponse(String input) {
        try {
            Command command = PARSER.parseForGui(input);
            return command.runForGui();
        } catch (JadeException e) {
            isErrorResponse = true;
            return e.getMessage();
        }
    }

    /**
     * Checks if the response was an error.
     *
     * @return True if the response was an error, false otherwise.
     */
    public boolean getErrorResponse() {
        return isErrorResponse;
    }

    /**
     * Sets whether the response should be considered an error.
     *
     * @param isError True if the response is an error, false otherwise.
     */
    public static void setErrorResponse(boolean isError) {
        isErrorResponse = isError;
    }
}
