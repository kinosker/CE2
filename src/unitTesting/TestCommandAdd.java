package unitTesting;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import command.CommandHandler;
import client.ConsolePrinter;

public class TestCommandAdd {

    ArrayList<String> storedList = new ArrayList<String>();
    ArrayList<String> expectedList = new ArrayList<String>();
    CommandHandler commandHandler = new CommandHandler(new ConsolePrinter(),
            storedList);

    @Test
    public void test() {
        testAddSingle();
        testAddMultiple();
        testAddWhiteSpace();
        testAddWhiteSpaces();
    }

    private void testAddWhiteSpace() {
        clearLists();
        commandHandler.executeCommand("add ");
        assertEquals(expectedList, commandHandler.getList());
    }

    private void testAddWhiteSpaces() {
        clearLists();
        commandHandler.executeCommand("add           ");
        assertEquals(expectedList, commandHandler.getList());
    }

    private void testAddSingle() {
        clearLists();
        expectedList.add("abc");

        commandHandler.executeCommand("add abc");
        assertEquals(expectedList, commandHandler.getList());

    }

    private void testAddMultiple() {
        clearLists();
        expectedList.add("abc");
        expectedList.add("xyz");
        commandHandler.executeCommand("add abc");
        commandHandler.executeCommand("add xyz");
        assertEquals(expectedList, commandHandler.getList());

    }

    private void clearLists() {
        expectedList.clear();
        storedList.clear();
    }

}
