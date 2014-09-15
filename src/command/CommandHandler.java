package command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;
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
			return new AddTask();
		case DISPLAY_COMMAND:
			return new DisplayTask();
		case CLEAR_COMMAND:
			return new ClearTask();
		case DELETE_COMMAND:
			return new DeleteTask();
		case SORT_COMMAND:
			return new SortTask();
		case SEARCH_COMMAND:
			return new searchTask();
		case EXIT_COMMAND:
			return new ExitTask();
		default:
			return new InvalidTask();
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
class AddTask implements Command {
	public Feedback execute() {
		String addedInput = CommandHandler._consoleScanner.nextLine();
		addedInput = addedInput.trim();
		CommandHandler.toDoList.add(addedInput);
		CommandHandler._consolePrinter.printAddSuccessful(addedInput);
		return Feedback.CONTINUE;
	}
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for ClearTask
 */
class ClearTask implements Command {
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
class DisplayTask implements Command {
	public Feedback execute() {
		if (CommandHandler.toDoList.isEmpty()) {
			CommandHandler._consolePrinter.printEmptyList();
		} else {

			ListIterator<String> _iterator = CommandHandler.toDoList
					.listIterator();
			CommandHandler._consolePrinter.printList(_iterator);
		}
		return Feedback.CONTINUE;
	}
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for DeleteTask
 */
class DeleteTask implements Command {
	public Feedback execute() {
		final int ARRAY_OFFSET = -1;
		int lineToDelete = extractInt() + ARRAY_OFFSET;
		if (lineToDelete < CommandHandler.toDoList.size() && lineToDelete >= 0) {
			String deletedInput = CommandHandler.toDoList.remove(lineToDelete);
			CommandHandler._consolePrinter.printDeleteSuccesful(deletedInput);
		} else {
			CommandHandler._consolePrinter.printIndexOutofBound();
		}
		return Feedback.CONTINUE;
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
class ExitTask implements Command {
	public Feedback execute() {
		return Feedback.EXIT;
	}
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for SortTask
 */
class SortTask implements Command {
	public Feedback execute() 
	{
		Collections.sort(CommandHandler.toDoList, alphabeticalSort);
		return Feedback.CONTINUE;
	}
	
	private static Comparator<String> alphabeticalSort = new Comparator<String>() {
	    public int compare(String firstString, String secondString) {
	        int result = String.CASE_INSENSITIVE_ORDER.compare(firstString, secondString);
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
class searchTask implements Command {
	public Feedback execute() {
		
		
		String searchInput = CommandHandler._consoleScanner.nextLine();
		searchInput = searchInput.trim();
		
		ArrayList <String> searchList = new ArrayList<String>(); 
		int index = 0;
        for (String string : CommandHandler.toDoList) {
        	index++;
            if(string.matches("^.*(?i)("+searchInput+").*")){
                searchList.add(index +". " + string);
            }
        }
     System.out.println(searchList);
		return Feedback.CONTINUE;
	}
}

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for InvalidTask
 */
class InvalidTask implements Command {
	public Feedback execute() {
		clearUserInput();
		CommandHandler._consolePrinter.printInvalid();
		return Feedback.CONTINUE;
	}

	private void clearUserInput() {
		if (CommandHandler._consoleScanner.hasNextLine())
		{
			CommandHandler._consoleScanner.nextLine();
		}
	}
}
