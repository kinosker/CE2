package client;

import java.util.ArrayList;
import java.util.Scanner;

import command.CommandHandler;
import fileLogger.FileHandler;

/**
 * @author TienLong This class contains the main function for the TextBuddy to
 *         run.
 */
public class TextBuddy {

	public enum Feedback {
		CONTINUE, EXIT
	};

	private static ConsolePrinter _consolePrinter = new ConsolePrinter();
	private static Scanner _consoleScanner = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int EXIT_INVALID_DATA = 13;
		final int EXIT_SUCCESS = 0;
		ArrayList<String> toDoList = new ArrayList<String>();
		Feedback _feedback;

		if (invalidArgs(args)) {
			invalidArgExit(EXIT_INVALID_DATA);
		}

		String fileName = args[0];
		FileHandler _fileHandler = new FileHandler(_consolePrinter,
				_consoleScanner, fileName);

		_fileHandler.buildFile(toDoList);
		_consolePrinter.printWelcome(_fileHandler.getFileName());
		_consolePrinter.setConfirmedFileName(_fileHandler.getFileName());

		do {
			_feedback = executeTextBuddy(toDoList);
		} while (_feedback != Feedback.EXIT);

		exitTextBuddy(EXIT_SUCCESS, toDoList, _fileHandler);

	}

	/**
	 * Display invalid arguments used and exit the program
	 * 
	 * @param EXIT_INVALID_DATA
	 *            the exit code
	 */
	private static void invalidArgExit(final int EXIT_INVALID_DATA) {
		_consolePrinter.printNoArgs();
		System.exit(EXIT_INVALID_DATA);
	}

	/**
	 * close all the scanner, handler and exit the program
	 * 
	 * @param EXIT_SUCCESS
	 *            the exit code
	 * @param toDoList
	 *            the list that store the to do task
	 * @param _fileHandler
	 *            the handler for the file
	 */
	private static void exitTextBuddy(final int EXIT_SUCCESS,
			ArrayList<String> toDoList, FileHandler _fileHandler) {
		_fileHandler.logFile(toDoList);
		_consoleScanner.close();
		_fileHandler.close();
		System.exit(EXIT_SUCCESS);
	}

	/**
	 * Execute text buddy and get user command
	 * 
	 * @param toDoList
	 *            the list that store the task to do
	 * @return Feedback the feedback whether to continue or exit
	 */
	private static Feedback executeTextBuddy(ArrayList<String> toDoList) {
		CommandHandler _commandHandler = new CommandHandler(_consolePrinter,
				_consoleScanner, toDoList);

		String userInput = getUserInput();
		Feedback _feedback = _commandHandler.executeCommand(userInput);
		return _feedback;

	}

	/**
	 * Check if the arguments is empty
	 * 
	 * @param args
	 *            the input arguments
	 * @return boolean whether the argument is invalid
	 */
	private static boolean invalidArgs(String[] args) {
		return args.length <= 0;
	}

	/**
	 * @return String the string that contains the user input
	 */
	private static String getUserInput() {
		_consolePrinter.printGetCommand();
		String userInput = _consoleScanner.next();
		return userInput;
	}

}
