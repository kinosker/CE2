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
        
        assertEquals( expectedList , _searchEngine.searchCaseInsensitive(storedList, "pig"));
    }

    private void testSearchSpaceNoList(SearchEngine _searchEngine) {
        clearLists();
        assertEquals( expectedList , _searchEngine.searchCaseInsensitive(storedList, " "));
    }

    private void testSearchSpaceNotFound(SearchEngine _searchEngine) {
        clearLists();
        storedList.add("horse");
        storedList.add("bat");
        storedList.add("bird");
        assertEquals( expectedList , _searchEngine.searchCaseInsensitive(storedList, " "));
    }

    private void testSearchEmptyString(SearchEngine _searchEngine) {
        clearLists();
        storedList.add("horse");
        storedList.add("bat");
        storedList.add("bird");
        assertEquals( expectedList , _searchEngine.searchCaseInsensitive(storedList, ""));
    }

    private void testSearchNull(SearchEngine _searchEngine) {
        clearLists();
        storedList.add("horse");
        storedList.add("bat");
        storedList.add("bird");
        assertEquals( expectedList , _searchEngine.searchCaseInsensitive(storedList, null));
        
    }

    private void testSearch1Found(SearchEngine _searchEngine) {
        clearLists();
        storedList.add("horse");
        storedList.add("bat");
        storedList.add("bird");
        expectedList.add("3. bird");
        assertEquals( expectedList , _searchEngine.searchCaseInsensitive(storedList, " bird"));
    }

    private void testSearchEmptyList(SearchEngine _searchEngine)
    {
        clearLists();
        assertEquals( expectedList , _searchEngine.searchCaseInsensitive(null, ""));
    }
    
    private void testSearchNotFound(SearchEngine _searchEngine)
    {
        clearLists();
        storedList.add("horse");
        storedList.add("bat");
        storedList.add("bird");
        assertEquals( expectedList , _searchEngine.searchCaseInsensitive(storedList, "pig"));
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
        testSortMultiTask();
        testSortCaseInsensitive();
    }

    private void testSortCaseInsensitive() {
        clearLists();
        storedList.add("pig");
        storedList.add("bat");
        expectedList.add("bat");
        expectedList.add("pig");
        setInputStream("sort");
        executeCommandTest();
        
    }


    private void testSortMultiTask() {
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
