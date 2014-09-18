package unitTesting;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import client.ConsolePrinter;
import command.CommandHandler;

public class TestCommandDelete {
    ArrayList<String> storedList = new ArrayList<String>();
    ArrayList<String> expectedList = new ArrayList<String>();
    CommandHandler commandHandler = new CommandHandler(new ConsolePrinter(), storedList);

    @Test
    public void test() {
        testDeleteAllTask();
        testDelete2ndTask();
        testDeleteBlank();
        testDeleteMultiple();

    }

    private void clearLists() {
        expectedList.clear();
        storedList.clear();
    }
    
    private void testDeleteMultiple() {
        clearLists();
        storedList.add("pig");
        storedList.add("shall");
        storedList.add("not");
        storedList.add("kill");
        storedList.add("me");
        storedList.add("and");
        storedList.add("you");
        
        expectedList = cloneList(storedList);
        expectedList.remove("not");
        expectedList.remove("me");
        expectedList.remove("and");

       commandHandler.executeCommand("delete 3");
       commandHandler.executeCommand("delete 4");
       commandHandler.executeCommand("delete 4");
       
        assertEquals(expectedList, commandHandler.getList());

        
        
    }

    private void testDeleteBlank() {
        clearLists();
        storedList.add("pig");
        storedList.add("eat");
        storedList.add("me");
        storedList.add("NOT NOW!!!");
        expectedList = cloneList(storedList);

        commandHandler.executeCommand("delete ");
        assertEquals(expectedList, commandHandler.getList());

    }

    private void testDelete2ndTask() {
        clearLists();
        storedList.add("pig");
        storedList.add("eat");
        storedList.add("me");
        storedList.add("NOT NOW!!!");
        
        expectedList = cloneList(storedList);
        expectedList.remove("eat");
        commandHandler.executeCommand("delete 2");
        assertEquals(expectedList, commandHandler.getList());
    }

    private void testDeleteAllTask() {
        clearLists();
        storedList.add("pig");
        commandHandler.executeCommand("delete 1");
        assertEquals(expectedList, commandHandler.getList());        
    }

    public static ArrayList<String> cloneList(ArrayList<String> list) {
        ArrayList<String> clone = new ArrayList<String>(list.size());
        for (String item : list)
            clone.add(item);
        return clone;
    }


}
