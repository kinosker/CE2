package unitTesting;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import command.CommandHandler;
import command.SearchEngine;
import client.ConsolePrinter;

public class TestingCE2 {
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> storedList = new ArrayList<String>();
    ArrayList<String> expectedList = new ArrayList<String>();
    @Test
    public void test() {
        
        testAddTask();
        testSearchTask();
        testSortTask();
        testClearTask();
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
        setInputStream("clear");
        executeCommandTest();
    }


    private void testClear1Task() {
        clearLists();
        storedList.add("pig");
        setInputStream("clear");
        executeCommandTest();
    }


    private void testSearchTask() {
        SearchEngine _searchEngine = new SearchEngine();     
        testSearchEmptyList(_searchEngine);
        testSearchNotFound(_searchEngine);
        testSearchNull(_searchEngine);
        testSearchEmptyString(_searchEngine);
        testSearchSpaceNotFound(_searchEngine);
        testSearch1Found(_searchEngine);
        testSearchSpaceNoList(_searchEngine);
        testSearchFoundAlot(_searchEngine);
    }
   
    private void testSearchFoundAlot(SearchEngine _searchEngine) {
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
        
        assertEquals( expectedList , _searchEngine.searchCaseSensitive(storedList, "pig"));
    }

    private void testSearchSpaceNoList(SearchEngine _searchEngine) {
        clearLists();
        assertEquals( expectedList , _searchEngine.searchCaseSensitive(storedList, " "));
    }

    private void testSearchSpaceNotFound(SearchEngine _searchEngine) {
        clearLists();
        storedList.add("horse");
        storedList.add("bat");
        storedList.add("bird");
        assertEquals( expectedList , _searchEngine.searchCaseSensitive(storedList, " "));
    }

    private void testSearchEmptyString(SearchEngine _searchEngine) {
        clearLists();
        storedList.add("horse");
        storedList.add("bat");
        storedList.add("bird");
        assertEquals( expectedList , _searchEngine.searchCaseSensitive(storedList, ""));
    }

    private void testSearchNull(SearchEngine _searchEngine) {
        clearLists();
        storedList.add("horse");
        storedList.add("bat");
        storedList.add("bird");
        assertEquals( expectedList , _searchEngine.searchCaseSensitive(storedList, null));
        
    }

    private void testSearch1Found(SearchEngine _searchEngine) {
        clearLists();
        storedList.add("horse");
        storedList.add("bat");
        storedList.add("bird");
        expectedList.add("3. bird");
        assertEquals( expectedList , _searchEngine.searchCaseSensitive(storedList, " bird"));
    }

    private void testSearchEmptyList(SearchEngine _searchEngine)
    {
        clearLists();
        assertEquals( expectedList , _searchEngine.searchCaseSensitive(null, ""));
    }
    
    private void testSearchNotFound(SearchEngine _searchEngine)
    {
        clearLists();
        storedList.add("horse");
        storedList.add("bat");
        storedList.add("bird");
        assertEquals( expectedList , _searchEngine.searchCaseSensitive(storedList, "pig"));
    }

    private void testAddTask()
    {
        testAddSingle();
        testAddMultiple();   
        testAddWhiteSpace();
        testAddWhiteSpaces();
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
    
    

    private void testSortTask()
    {
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
        setInputStream("sort");
        executeCommandTest();
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
        setInputStream("sort");
        executeCommandTest();
        
    }


    private void testSort2Task() {
        clearLists();
        storedList.add("pig");
        storedList.add("bat");
        expectedList.add("bat");
        expectedList.add("pig");
        setInputStream("sort");
        executeCommandTest();
    }


    private void testSort1Task() {
        clearLists();
        storedList.add("bat");
        expectedList.add("bat");
        setInputStream("sort");
        executeCommandTest();
    }


    private void testSortNoList() {
        clearLists();
        setInputStream("sort");
        executeCommandTest();
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
