package client;

import java.util.ArrayList;
import java.util.ListIterator;

public class ConsolePrinter {

    private String confirmedFileName;

    public void setConfirmedFileName(String fileName) {
        this.confirmedFileName = fileName;
    }

    public void printFileExist(String fileName) {
        System.out.println(confirmedFileName + "already exist");
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

    public void printWelcome() {
        System.out.println("Welcome to TextBuddy. " + confirmedFileName
                + " is ready for use");
    }

    public void printNoArgs() {
        System.out.println("No arguments passed into the programme");
        System.out.println("System exiting....");
    }

    public void printGetCommand() {
        System.out.print("command: ");
    }

    public void printAddSuccess(String addedInput) {
        System.out.println("added to " + confirmedFileName + ": \""
                + addedInput + "\"");
    }

    public void printDeleteSuccess(String deletedInput) {
        System.out.println("Delete from " + confirmedFileName + ": \""
                + deletedInput + "\"");
    }

    public void printDeleteFail() {
        System.out
                .println("The index you are trying to delete is not available");
    }

    public void printClear() {
        System.out.println("All content deleted from " + confirmedFileName);
    }

    public void printList(ArrayList<String> list, boolean addIndex) {

        if (addIndex) {
            final int INITIAL_COUNT = 1;
            int count = INITIAL_COUNT;
            ListIterator<String> iterator = list.listIterator();
            while (iterator.hasNext()) {
                System.out.println(count + ". " + iterator.next());
                count++;
            }
        }
        else
        {
            printList(list);
        }
    }

    public void printList(ArrayList<String> list) {
        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void printEmptyList() {
        System.out.println(confirmedFileName + " is empty");
    }
    
    public void printSortSuccess()
    {
        System.out.println("Sort Successful");
    }

}
