package client;

import java.util.ListIterator;

public class ConsolePrinter {

	private String confirmedFileName;

	public void setConfirmedFileName(String fileName) {
		this.confirmedFileName = fileName;
	}

	public void printFileExist(String fileName) {
		System.out.println(fileName + " already exist");
		System.out
				.println("Do you want to append(a), overwrite(w) or reselect another file name(r)");
		System.out.print("Select (a), (w), (r) : ");
	}

	public void printInvalid() {
		System.out.println("Invalid input, please try again");
	}

	public void printPromptFileName() {
		System.out.print("Kindly enter the new file name : ");
	}

	public void printWelcome(String fileName) {
		System.out.println("Welcome to TextBuddy. " + fileName
				+ " is ready for use");
	}

	public void printNoArgs() {
		System.out.println("No arguments passed into the programme");
		System.out.println("System exiting....");
	}

	public void printGetCommand() {
		System.out.print("command: ");
	}

	public void printAddSuccessful(String addedInput) {
		System.out.println("added to " + confirmedFileName + ": \""
				+ addedInput + "\"");
	}

	public void printDeleteSuccesful(String deletedInput) {
		System.out.println("Delete from " + confirmedFileName + ": \""
				+ deletedInput + "\"");
	}

	public void printIndexOutofBound() {
		System.out.println("The index you are deleting is not available");
	}

	public void printClear() {
		System.out.println("All content deleted from " + confirmedFileName);
	}

	public void printList(ListIterator<String> _iterator) {
		final int initialCount = 1;
		int count = initialCount;
		while (_iterator.hasNext()) {
			System.out.println(count + ". " + _iterator.next());
			count++;
		}
	}

	public void printEmptyList() {
		System.out.println(confirmedFileName + " is empty");
	}

}
