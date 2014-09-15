package unitTesting;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import command.CommandHandler;
import client.ConsolePrinter;

public class TestingCE2 {
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> inputList = new ArrayList<String>();
    ArrayList<String> expectedList = new ArrayList<String>();
    @Test
    public void test() {
        
        
        testAddTask();
        
    }

    private void testAddTask()
    {
        testSingleAdd();
        testMultipleAdd();   
        testAddBlank();
    }

    private void testAddBlank() {
        clearLists();
        setInputStream("add ");
        executeCommandTest();
    }

    private void testSingleAdd() {
        clearLists();
        setInputStream("add abc");
        expectedList.add("abc");
        executeCommandTest();
    }

    private void testMultipleAdd() {
        clearLists();
        setInputStream("add abc\n add xyz");
        expectedList.add("abc");
        expectedList.add("xyz");
        executeCommandTest();
    }

    private void clearLists() {
        expectedList.clear();
        inputList.clear();
    }

    private void executeCommandTest() {
        CommandHandler commandHandler = new CommandHandler(new ConsolePrinter(), scanner, inputList);
        while(scanner.hasNext())
        commandHandler.executeCommand(scanner.next());
        assertEquals(expectedList, commandHandler.getList());
    }
    
    
    private void setInputStream(String userInput) 
    {
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        scanner = new Scanner(System.in);
    }
}
