package fileLogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import client.ConsolePrinter;

/**
 * @author TienLong Handles all file operation such as logging of file, parsing
 *         of file etc.
 */
public class FileHandler {

	enum FileCommandType {
		FILE_APPEND, FILE_OVERWRITE, FILE_RENAME, FILE_INVALID
	};

	private Logger logger = null;
	private BufferedWriter writer = null;
	private String fileName;
	private FileCommandType fileCommand;
	Scanner consoleScanner;
	ConsolePrinter consolePrinter;

	/*
	 * Store selected fileName and select default logger ( TxtLogger ) that the
	 * program will be using
	 */
	/**
	 * Constructor for FileHandler, with TxtLogger selected as the default
	 * logger
	 * 
	 * @param consolePrinter
	 *            for printing output in console
	 * @param consoleScanner
	 *            for scanning input in console
	 * @param fileName
	 *            the fileName that will be manipulate
	 */
	public FileHandler(ConsolePrinter consolePrinter, Scanner consoleScanner,
			String fileName) {
		this.consolePrinter = consolePrinter;
		this.consoleScanner = consoleScanner;
		this.fileName = fileName;
		this.logger = new TxtLogger();
	}

	/**
	 * Constructor for FileHandler
	 * 
	 * @param consoleScanner
	 *            for scanning input in console
	 * @param fileName
	 *            the fileName that will be manipulate
	 * @param logger
	 *            the logger which decides how the output should be logged
	 */
	public FileHandler(ConsolePrinter consolePrinter, Scanner consoleScanner,
			String fileName, Logger logger) {
		this.consolePrinter = consolePrinter;
		this.consoleScanner = consoleScanner;
		this.fileName = fileName;
		this.logger = logger;
	}

	/**
	 * Return fileName
	 * 
	 * @return fileName the fileName that will be manipulate
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * Wrapper function for logContent
	 * <p>
	 * This function first make use of the Logger class function: logContent.
	 * The logContent function uses the contents from the toDoList and log them
	 * to the targeted file.
	 * <p>
	 * 
	 * @param toDoList
	 *            the list that store the tasks to do
	 */
	public void logFile(ArrayList<String> toDoList) {
		logger.logContent(writer, toDoList);
	}

	/**
	 * This function handles the creating or loading of the file
	 * <p>
	 * This function handles the creating of the file, next it will check
	 * whether the file exist If the file exist
	 * <p>
	 * 
	 * @param toDoList
	 */
	public void buildFile(ArrayList<String> toDoList) {
		checkFileExist();
		if (appendFile()) {
			storeExistingFileData(toDoList);
		}
		createFile();
	}

	/**
	 * Get existing data from the file and stored it to the toDoList
	 * 
	 * @param toDoList
	 *            the list to store the task to do
	 */
	private void storeExistingFileData(ArrayList<String> toDoList) {
		Parser parser = new Parser(toDoList);
		parser.readContent(fileName);
	}

	/**
	 * Set FileCommandType to append, to allow appending of the file
	 * 
	 * @return FileCommandType the command that will decide the file operation
	 */
	private boolean appendFile() {
		return fileCommand == FileCommandType.FILE_APPEND;
	}

	/**
	 * Wrapper function for closing the writer
	 */
	public void close() {
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Check if the file exist
	 */
	private void checkFileExist() {
		if (fileExist()) {
			handleFileExist();
		}
	}

	/**
	 * Create the file
	 */
	private void createFile() {
		try {
			FileWriter fileCreated = new FileWriter(fileName);
			this.writer = new BufferedWriter(fileCreated);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return boolean the condition if the file exist
	 */
	private boolean fileExist() {
		File file = new File(fileName);
		return file.exists();
	}

	/**
	 * Determine whether the user want to rename, overwrite or append the file
	 */
	private void handleFileExist() {
		boolean invalidInput = false;

		String userInput;

		do {
			consolePrinter.printFileExist(fileName);
			userInput = consoleScanner.nextLine();
			fileCommand = determineFileCommand(userInput);
			switch (fileCommand) {
			case FILE_APPEND:
				break;
			case FILE_OVERWRITE:
				break;
			case FILE_RENAME:
				getNewFileName();
				invalidInput = fileExist();
				break;
			case FILE_INVALID:
				consolePrinter.printInvalid();
				break;
			}
		} while (invalidInput);
	}

	/**
	 * Determine the file command based on the userInput
	 * 
	 * @param userInput
	 *            the input that determine whether to append, rename or
	 *            overwrite
	 * @return FileCommandType the command to determine the file operation
	 */
	private FileCommandType determineFileCommand(String userInput) {
		final String APPEND_FILE = "a";
		final String OVERWRITE_FILE = "w";
		final String RENAME_FILE = "r";

		switch (userInput.toLowerCase()) {
		case APPEND_FILE:
			return FileCommandType.FILE_APPEND;
		case OVERWRITE_FILE:
			return FileCommandType.FILE_OVERWRITE;
		case RENAME_FILE:
			return FileCommandType.FILE_RENAME;
		default:
			return FileCommandType.FILE_INVALID;

		}

	}

	private void getNewFileName() {
		consolePrinter.printPromptFileName();
		fileName = consoleScanner.nextLine();
	}

}
