package command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import client.ConsolePrinter;
import client.TextBuddy.Feedback;

/**
 * @author TienLong This class handle all the commands passed in by the user
 * 
 */
public class CommandHandler {

    static ArrayList<String> toDoList = null;
    static ConsolePrinter consolePrinter;

    /**
     * Constructor for CommandHandler
     * 
     * @param consolePrinter
     *            for printing output in console
     * @param consoleScanner
     *            for scanning input in console
     * @param toDoList
     *            the list that store the task
     */
    public CommandHandler(ConsolePrinter consolePrinter, ArrayList<String> toDoList) {
        CommandHandler.consolePrinter = consolePrinter;
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
        String userCommand = StringHandler.getFirstWord(userInput);
        String userArguments = StringHandler.removeFirstMatched(userInput, userCommand);
        Command command = determineCommand(userCommand);
        return command.execute(userArguments);
    }

    public ArrayList<String> getList() {
        return CommandHandler.toDoList;

    }

    /**
     * Determine which command to do based on user input
     * 
     * @param userCommand
     *            the input to determine the command
     * @return Command the Command that will be executed
     */
    public Command determineCommand(String userCommand) {
        final String ADD_COMMAND = "add";
        final String DISPLAY_COMMAND = "display";
        final String CLEAR_COMMAND = "clear";
        final String EXIT_COMMAND = "exit";
        final String DELETE_COMMAND = "delete";
        final String SORT_COMMAND = "sort";
        final String SEARCH_COMMAND = "search";

        switch (userCommand.toLowerCase()) {
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
     * @param userArguments TODO
     * 
     * @return feedback to continue or exit
     */
    Feedback execute(String userArguments);
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for AddTask
 */
class AddCommand implements Command {
    public Feedback execute(String userArguments) {
        
        userArguments = userArguments.trim();
        if (canAdd(userArguments)) {
            CommandHandler.toDoList.add(userArguments);
            CommandHandler.consolePrinter.printAddSuccess(userArguments);
        } else {
            CommandHandler.consolePrinter.printInvalid();
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
    public Feedback execute(String userArguments) {
        CommandHandler.toDoList.clear();
        CommandHandler.consolePrinter.printClear();
        return Feedback.CONTINUE;
    }
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for DisplayTask
 */
class DisplayCommand implements Command {
    public Feedback execute(String userArguments) {
        if (CommandHandler.toDoList.isEmpty()) {
            CommandHandler.consolePrinter.printEmptyList();
        } else {

            final boolean PRINT_INDEX = true;
            CommandHandler.consolePrinter.printList(CommandHandler.toDoList, PRINT_INDEX);
        }
        return Feedback.CONTINUE;
    }
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for DeleteTask
 */
class DeleteCommand implements Command {
    
    final int INVALID_INDEX = -1;
    public Feedback execute(String userArguments) {
        final int ARRAY_OFFSET = -1;
        int lineToDelete = getLineIndex(userArguments) + ARRAY_OFFSET;
        if (canDelete(lineToDelete)) {
            String deletedInput = CommandHandler.toDoList.remove(lineToDelete);
            CommandHandler.consolePrinter.printDeleteSuccess(deletedInput);

        } else {
            CommandHandler.consolePrinter.printDeleteFail();

        }
        return Feedback.CONTINUE;
    }

    private boolean canDelete(int lineToDelete) {
        return lineToDelete < CommandHandler.toDoList.size()
                && lineToDelete >= 0;
    }

    private int getLineIndex(String userArguments) {
        
  
        if(isValidString(userArguments))
        {
            userArguments = userArguments.trim();
            if(isInteger(userArguments))
            {
                return Integer.parseInt(userArguments);
            }
        }
        return INVALID_INDEX;
    }
    
    private boolean isInteger(String userInput) {
        
        try
        {
            Integer.parseInt(userInput);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    private boolean isValidString(String addedInput) {
        return addedInput.length() > 0;
    }
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for ExitTask
 */
class ExitCommand implements Command {
    public Feedback execute(String userArguments) {
        return Feedback.EXIT;
    }
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for SortTask
 */
class SortCommand implements Command {
    public Feedback execute(String userArguments) {
        Collections.sort(CommandHandler.toDoList, alphabeticalSort);
        final boolean PRINT_INDEX = true;
        CommandHandler.consolePrinter.printSortSuccess();
        CommandHandler.consolePrinter.printList(CommandHandler.toDoList, PRINT_INDEX);
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
    public Feedback execute(String userArguments) {
        SearchEngine searchEngine = new SearchEngine();
        ArrayList<String> searchList  = searchEngine.searchCaseSensitive(CommandHandler.toDoList, userArguments);
        if (searchList.isEmpty()) 
        {
            System.out.println("No result found");
        } else {
            CommandHandler.consolePrinter.printList(searchList);
        }
        return Feedback.CONTINUE;
    }

}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for InvalidTask
 */
class InvalidCommand implements Command {
    public Feedback execute(String userArguments) {
        CommandHandler.consolePrinter.printInvalid();
        return Feedback.CONTINUE;
    }
}
