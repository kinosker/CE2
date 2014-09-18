package unitTesting;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import client.ConsolePrinter;

import command.CommandHandler;

public class TestCommandSort {
    ArrayList<String> storedList = new ArrayList<String>();
    ArrayList<String> expectedList = new ArrayList<String>();
    CommandHandler commandHandler = new CommandHandler(new ConsolePrinter(), storedList);
    

    @Test
    public void test() {
        testSortNoList();
        testSort1Task();
        testSort2Task();
        testSortCaseSensitive();
        testSortMulitpleTask();
    }

    private void testSortMulitpleTask() {
        clearLists();
        storedList.add("pig");
        storedList.add("Monkey");
        storedList.add("PIG");
        storedList.add("Food for PiG");
        storedList.add("monkey");
        storedList.add("PiG");
        storedList.add("monkey");
        storedList.add("bannaaaaanaaaaa");
        storedList.add("piG");
        storedList.add("Apple for u?");

        expectedList.add("Apple for u?");
        expectedList.add("bannaaaaanaaaaa");
        expectedList.add("Food for PiG");
        expectedList.add("Monkey");
        expectedList.add("monkey");
        expectedList.add("monkey");
        expectedList.add("PIG");
        expectedList.add("PiG");
        expectedList.add("piG");
        expectedList.add("pig");
        commandHandler.executeCommand("sort");
        assertEquals(expectedList, commandHandler.getList());
    }

    private void testSortCaseSensitive() {
        clearLists();
        storedList.add("pig");
        storedList.add("PIG");
        storedList.add("PiG");
        storedList.add("piG");
        expectedList.add("PIG");
        expectedList.add("PiG");
        expectedList.add("piG");
        expectedList.add("pig");
        commandHandler.executeCommand("sort");
        assertEquals(expectedList, commandHandler.getList());
    }

    private void testSort2Task() {
        clearLists();
        storedList.add("pig");
        storedList.add("bat");
        expectedList.add("bat");
        expectedList.add("pig");
        commandHandler.executeCommand("sort");
        assertEquals(expectedList, commandHandler.getList());        
    }

    private void testSort1Task() {
        clearLists();
        storedList.add("bat");
        expectedList.add("bat");
        commandHandler.executeCommand("sort");
        assertEquals(expectedList, commandHandler.getList());

    }

    private void testSortNoList() {
        clearLists();
        commandHandler.executeCommand("sort");
        assertEquals(expectedList, commandHandler.getList());
    }

    private void clearLists() {
        expectedList.clear();
        storedList.clear();
    }
}
