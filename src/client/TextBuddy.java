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
    private static final int EXIT_INVALID_DATA = 13;
    private static final int EXIT_SUCCESS = 0;

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ArrayList<String> toDoList = new ArrayList<String>();

        if (noArgument(args)) {
            invalidArgumentExit();
        }

        String fileName = args[0];
        FileHandler _fileHandler = new FileHandler(_consolePrinter,
                _consoleScanner, fileName);

        _fileHandler.buildFile(toDoList);
        _consolePrinter.setConfirmedFileName(_fileHandler.getFileName());
        _consolePrinter.printWelcome();
        
        executeTextBuddy(toDoList, _fileHandler);

    }

    /**
     * Execute text buddy by getting command from user
     * 
     */
    private static void executeTextBuddy(ArrayList<String> toDoList,
            FileHandler _fileHandler) {
        Feedback _feedback;
        do {
            CommandHandler _commandHandler = new CommandHandler(
                    _consolePrinter, _consoleScanner, toDoList);
            String userInput = getUserInput();
            _feedback = _commandHandler.executeCommand(userInput);
        } while (_feedback != Feedback.EXIT);

        exitTextBuddy(toDoList, _fileHandler);
    }

    /**
     * Display invalid arguments used and exit the program
     * 
     * @param EXIT_INVALID_DATA
     *            the exit code
     */
    private static void invalidArgumentExit() {
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
    private static void exitTextBuddy(ArrayList<String> toDoList,
            FileHandler _fileHandler) {
        _fileHandler.logFile(toDoList);
        _consoleScanner.close();
        _fileHandler.close();
        System.exit(EXIT_SUCCESS);
    }

    /**
     * Check if the arguments is empty
     * 
     * @param args
     *            the input arguments
     * @return boolean whether the argument is invalid
     */
    private static boolean noArgument(String[] args) {
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
