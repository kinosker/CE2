package unitTesting;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import command.CommandHandler;
import command.SearchEngine;
import client.ConsolePrinter;

public class TestingCE2 {
    ArrayList<String> storedList = new ArrayList<String>();
    ArrayList<String> expectedList = new ArrayList<String>();
    CommandHandler commandHandler = new CommandHandler(new ConsolePrinter(), storedList);
    @Test
    public void test() {

        testAddTask();
        testSearchTask();
        testSortTask();
        testClearTask();
        testDeleteTask();
    }



    private void testDeleteTask() {
        testDeleteAllTask();
        testDelete2ndTask();
        testDeleteBlank();
        testDeleteMultiple();

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

    private void testClearTask() {
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

    private void testSearchTask() {
        SearchEngine searchEngine = new SearchEngine();
        testSearchEmptyList(searchEngine);
        testSearchNotFound(searchEngine);
        testSearchNull(searchEngine);
        testSearchEmptyString(searchEngine);
        testSearchSpaceNotFound(searchEngine);
        testSearch1Found(searchEngine);
        testSearchSpaceNoList(searchEngine);
        testSearchFoundAlot(searchEngine);
    }

    private void testSearchFoundAlot(SearchEngine searchEngine) {
        clearLists();
        storedList.add("pig");
        storedList.add("bat");
        storedList.add("bird");
        storedList.add("I am Piggy");
        storedList.add("Is he a pig, i dont know?");
        storedList.add("PiG");
        storedList.add("dont find me!!");
        storedList.add("@pIg@");
        storedList.add("I              pig");
        storedList.add("feedMe");
        storedList.add("findMeIamTheHidDeNPig");

        expectedList.add("1. pig");
        expectedList.add("4. I am Piggy");
        expectedList.add("5. Is he a pig, i dont know?");
        expectedList.add("6. PiG");
        expectedList.add("8. @pIg@");
        expectedList.add("9. I              pig");
        expectedList.add("11. findMeIamTheHidDeNPig");

        assertEquals(expectedList,
                searchEngine.searchCaseSensitive(storedList, "pig"));
    }

    private void testSearchSpaceNoList(SearchEngine searchEngine) {
        clearLists();
        assertEquals(expectedList,
                searchEngine.searchCaseSensitive(storedList, " "));
    }

    private void testSearchSpaceNotFound(SearchEngine searchEngine) {
        clearLists();
        storedList.add("horse");
        storedList.add("bat");
        storedList.add("bird");
        assertEquals(expectedList,
                searchEngine.searchCaseSensitive(storedList, " "));
    }

    private void testSearchEmptyString(SearchEngine searchEngine) {
        clearLists();
        storedList.add("horse");
        storedList.add("bat");
        storedList.add("bird");
        assertEquals(expectedList,
                searchEngine.searchCaseSensitive(storedList, ""));
    }

    private void testSearchNull(SearchEngine searchEngine) {
        clearLists();
        storedList.add("horse");
        storedList.add("bat");
        storedList.add("bird");
        assertEquals(expectedList,
                searchEngine.searchCaseSensitive(storedList, null));

    }

    private void testSearch1Found(SearchEngine searchEngine) {
        clearLists();
        storedList.add("horse");
        storedList.add("bat");
        storedList.add("bird");
        expectedList.add("3. bird");
        assertEquals(expectedList,
                searchEngine.searchCaseSensitive(storedList, " bird"));
    }

    private void testSearchEmptyList(SearchEngine searchEngine) {
        clearLists();
        assertEquals(expectedList, searchEngine.searchCaseSensitive(null, ""));
    }

    private void testSearchNotFound(SearchEngine searchEngine) {
        clearLists();
        storedList.add("horse");
        storedList.add("bat");
        storedList.add("bird");
        assertEquals(expectedList,
                searchEngine.searchCaseSensitive(storedList, "pig"));
    }

    private void testAddTask() {
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

    private void testSortTask() {
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
}


 