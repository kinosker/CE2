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
        INVALID_TASK, CONTINUE, EXIT
    };

    private static ConsolePrinter consolePrinter = new ConsolePrinter();
    private static Scanner consoleScanner = new Scanner(System.in);
    private static final int EXIT_INVALID_DATA = 13;
    private static final int EXIT_SUCCESS = 0;

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ArrayList<String> toDoList = new ArrayList<String>();

        if (noArgument(args)) {
            invalidArgumentExit();
        }

        String fileName = args[0];
        FileHandler fileHandler = new FileHandler(consolePrinter,
                consoleScanner, fileName);

        fileHandler.buildFile(toDoList);
        consolePrinter.setConfirmedFileName(fileHandler.getFileName());
        consolePrinter.printWelcome();
        
        executeTextBuddy(toDoList, fileHandler);

    }

    /**
     * Execute text buddy by getting command from user
     * 
     */
    private static void executeTextBuddy(ArrayList<String> toDoList,
            FileHandler fileHandler) {
        Feedback feedback;
        do {
            CommandHandler commandHandler = new CommandHandler(
                    consolePrinter, toDoList);
            feedback = commandHandler.executeCommand(getUserInput());
        } while (feedback != Feedback.EXIT);

        exitTextBuddy(toDoList, fileHandler);
    }

    /**
     * Display invalid arguments used and exit the program
     * 
     * @param EXIT_INVALID_DATA
     *            the exit code
     */
    private static void invalidArgumentExit() {
        consolePrinter.printNoArgs();
        System.exit(EXIT_INVALID_DATA);
    }

    /**
     * close all the scanner, handler and exit the program
     * 
     * @param EXIT_SUCCESS
     *            the exit code
     * @param toDoList
     *            the list that store the to do task
     * @param fileHandler
     *            the handler for the file
     */
    private static void exitTextBuddy(ArrayList<String> toDoList,
            FileHandler fileHandler) {
        fileHandler.logFile(toDoList);
        consoleScanner.close();
        fileHandler.close();
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
        consolePrinter.printGetCommand();
        String userInput = consoleScanner.nextLine();
        return userInput;
    }

}
