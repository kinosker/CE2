package command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import client.ConsolePrinter;
import client.TextBuddy.Feedback;

/**
 * @author TienLong This class handle all the commands passed in by the user
 * 
 */
public class CommandHandler {

    static ArrayList<String> toDoList = null;
    static Scanner _consoleScanner; // default modifier for package and class
                                    // usage
    static ConsolePrinter _consolePrinter;

    /**
     * Constructor for CommandHandler
     * 
     * @param _consolePrinter
     *            for printing output in console
     * @param _consoleScanner
     *            for scanning input in console
     * @param toDoList
     *            the list that store the task
     */
    public CommandHandler(ConsolePrinter _consolePrinter,
            Scanner _consoleScanner, ArrayList<String> toDoList) {
        CommandHandler._consolePrinter = _consolePrinter;
        CommandHandler._consoleScanner = _consoleScanner;
        CommandHandler.toDoList = toDoList;
    }

    /**
     * Execute Command based on userInput
     * 
     * @param userInput
     *            the input to execute command
     * @return Feedback to continue or exit textBuddy
     */
    public Feedback executeCommand(String userInput) {
        Command _command = determineCommand(userInput);
        return _command.execute();
    }

    public ArrayList<String> getList() {
        return CommandHandler.toDoList;

    }

    /**
     * Determine which command to do based on user input
     * 
     * @param userInput
     *            the input to determine the command
     * @return Command the Command that will be executed
     */
    public Command determineCommand(String userInput) {
        final String ADD_COMMAND = "add";
        final String DISPLAY_COMMAND = "display";
        final String CLEAR_COMMAND = "clear";
        final String EXIT_COMMAND = "exit";
        final String DELETE_COMMAND = "delete";
        final String SORT_COMMAND = "sort";
        final String SEARCH_COMMAND = "search";

        switch (userInput.toLowerCase()) {
        case ADD_COMMAND:
            return new AddCommand();
        case DISPLAY_COMMAND:
            return new DisplayCommand();
        case CLEAR_COMMAND:
            return new ClearCommand();
        case DELETE_COMMAND:
            return new DeleteCommand();
        case SORT_COMMAND:
            return new SortCommand();
        case SEARCH_COMMAND:
            return new SearchCommand();
        case EXIT_COMMAND:
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }
    }

}

/**
 * @author TienLong This interface provides the implementation(s) for the task
 *         commands
 */
interface Command {
    /**
     * This method execute the commands such as add, display, clear etc.
     * 
     * @return feedback to continue or exit
     */
    Feedback execute();
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for AddTask
 */
class AddCommand implements Command {
    public Feedback execute() {
        String taskDescription = CommandHandler._consoleScanner.nextLine();
        taskDescription = taskDescription.trim();
        if (canAdd(taskDescription)) {
            CommandHandler.toDoList.add(taskDescription);
            CommandHandler._consolePrinter.printAddSuccess(taskDescription);
        } else {
            CommandHandler._consolePrinter.printInvalid();
        }
        return Feedback.CONTINUE;
    }

    private boolean canAdd(String addedInput) {
        return addedInput.length() > 0;
    }
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for ClearTask
 */
class ClearCommand implements Command {
    public Feedback execute() {
        CommandHandler.toDoList.clear();
        CommandHandler._consolePrinter.printClear();
        return Feedback.CONTINUE;
    }
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for DisplayTask
 */
class DisplayCommand implements Command {
    public Feedback execute() {
        if (CommandHandler.toDoList.isEmpty()) {
            CommandHandler._consolePrinter.printEmptyList();
        } else {

            final boolean PRINT_INDEX = true;
            CommandHandler._consolePrinter.printList(CommandHandler.toDoList, PRINT_INDEX);
        }
        return Feedback.CONTINUE;
    }
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for DeleteTask
 */
class DeleteCommand implements Command {
    public Feedback execute() {
        final int ARRAY_OFFSET = -1;
        int lineToDelete = extractInt() + ARRAY_OFFSET;
        if (canDelete(lineToDelete)) {
            String deletedInput = CommandHandler.toDoList.remove(lineToDelete);
            CommandHandler._consolePrinter.printDeleteSuccess(deletedInput);

        } else {
            CommandHandler._consolePrinter.printDeleteFail();

        }
        return Feedback.CONTINUE;
    }

    private boolean canDelete(int lineToDelete) {
        return lineToDelete < CommandHandler.toDoList.size()
                && lineToDelete >= 0;
    }

    private int extractInt() {
        while (!CommandHandler._consoleScanner.hasNextInt()) {
            System.out.println("Kindly select a number to delete");
            CommandHandler._consoleScanner.nextLine();
        }
        int intInput = CommandHandler._consoleScanner.nextInt();
        CommandHandler._consoleScanner.nextLine();
        return intInput;
    }
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for ExitTask
 */
class ExitCommand implements Command {
    public Feedback execute() {
        return Feedback.EXIT;
    }
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for SortTask
 */
class SortCommand implements Command {
    public Feedback execute() {
        Collections.sort(CommandHandler.toDoList, alphabeticalSort);
        final boolean PRINT_INDEX = true;
        CommandHandler._consolePrinter.printSortSuccess();
        CommandHandler._consolePrinter.printList(CommandHandler.toDoList, PRINT_INDEX);
        return Feedback.CONTINUE;
    }

    private static Comparator<String> alphabeticalSort = new Comparator<String>() {
        public int compare(String firstString, String secondString) {
            int result = String.CASE_INSENSITIVE_ORDER.compare(firstString,
                    secondString);
            if (result == 0) {
                result = firstString.compareTo(secondString);
            }
            return result;
        }
    };

}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for searchTask
 */
class SearchCommand implements Command {
    public Feedback execute() {
        SearchEngine _searchEngine = new SearchEngine();
        String searchInput = getUserInput();
        ArrayList<String> searchList  = _searchEngine.searchCaseInsensitive(CommandHandler.toDoList, searchInput);
        if (searchList.isEmpty()) 
        {
            System.out.println("No result found");
        } else {
            CommandHandler._consolePrinter.printList(searchList);
        }
        return Feedback.CONTINUE;
    }

    private String getUserInput() {
        return CommandHandler._consoleScanner.nextLine();
    }


}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for InvalidTask
 */
class InvalidCommand implements Command {
    public Feedback execute() {
        clearUserInput();
        CommandHandler._consolePrinter.printInvalid();
        return Feedback.CONTINUE;
    }

    private void clearUserInput() {
        if (CommandHandler._consoleScanner.hasNextLine()) {
            CommandHandler._consoleScanner.nextLine();
        }
    }
}
