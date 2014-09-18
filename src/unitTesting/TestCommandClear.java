package unitTesting;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import client.ConsolePrinter;

import command.CommandHandler;

public class TestCommandClear {
    ArrayList<String> storedList = new ArrayList<String>();
    ArrayList<String> expectedList = new ArrayList<String>();
    CommandHandler commandHandler = new CommandHandler(new ConsolePrinter(), storedList);
    
    @Test
    public void test() {
        testClear1Task();
        testClearMultipleTasks();
    }

    private void testClearMultipleTasks() {
        clearLists();
        storedList.add("pig");
        storedList.add("eat");
        storedList.add("me");
        storedList.add("dont");
        storedList.add("eat");
        storedList.add("pig");
        commandHandler.executeCommand("clear");
        assertEquals(expectedList, commandHandler.getList());
    }

    private void testClear1Task() {
        clearLists();
        storedList.add("pig");
        commandHandler.executeCommand("clear");
        assertEquals(expectedList, commandHandler.getList());
    }
    
    private void clearLists() {
        expectedList.clear();
        storedList.clear();
    }

}
