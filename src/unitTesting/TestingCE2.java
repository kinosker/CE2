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
    ArrayList<String> storedList = new ArrayList<String>();
    ArrayList<String> expectedList = new ArrayList<String>();
    @Test
    public void test() {
        
        testAddTask();
        testSearchTask();
    }

    private void testSearchTask() {
        testSearchEmptyList();
      //  testSearchNotFound();
    }
   
    private void testSearchEmptyList()
    {
        clearLists();
        setInputStream("search pig");
        executeCommandTest();
    }
    
    private void testSearchNotFound()
    {
        clearLists();
        storedList.add("horse");
        storedList.add("bat");
        storedList.add("bird");
        setInputStream("search pig");
        executeCommandTest();
    }

    private void testAddTask()
    {
        testAddSingle();
        testAddMultiple();   
        testAddWhiteSpace();
        testAddWhiteSpaces();
    }
    
    private void testAddClass()
    {
        
    }

    private void testAddWhiteSpace() {
        clearLists();
        setInputStream("add ");
        executeCommandTest();
    }
    
    private void testAddWhiteSpaces() {
        clearLists();
        setInputStream("add           ");
        executeCommandTest();
    }

    private void testAddSingle() {
        clearLists();
        setInputStream("add abc");
        expectedList.add("abc");
        executeCommandTest();
    }

    private void testAddMultiple() {
        clearLists();
        setInputStream("add abc\n add xyz");
        expectedList.add("abc");
        expectedList.add("xyz");
        executeCommandTest();
    }

    private void clearLists() {
        expectedList.clear();
        storedList.clear();
    }

    private void executeCommandTest() {
        CommandHandler commandHandler = new CommandHandler(new ConsolePrinter(), scanner, storedList);
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
