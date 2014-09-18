package unitTesting;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import client.ConsolePrinter;
import command.CommandHandler;
import command.SearchEngine;

public class TestCommandSearch {
    

    ArrayList<String> storedList = new ArrayList<String>();
    ArrayList<String> expectedList = new ArrayList<String>();
    CommandHandler commandHandler = new CommandHandler(new ConsolePrinter(), storedList);

    @Test
    public void test() {
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
    

    private void clearLists() {
        expectedList.clear();
        storedList.clear();
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

}
