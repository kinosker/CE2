package unitTesting;

import static org.junit.Assert.*;

import org.junit.Test;

import command.StringHandler;

public class TestStringHandler {

    @Test
    public void test() {
        StringHandler stringHandler = new StringHandler();
        assertEquals("add", stringHandler.getFirstWord("add Pizza"));
        assertEquals(null, stringHandler.getFirstWord(""));
        assertEquals(null, stringHandler.getFirstWord("       "));
        assertEquals(null, stringHandler.getFirstWord(null));
        assertEquals("greedIsGood", stringHandler.getFirstWord("greedIsGood"));
        assertEquals("Pig", stringHandler.getFirstWord("Pig is flying !!!"));
        
        assertEquals("Pizza", stringHandler.removeFirstMatched("add Pizza", "add"));
        assertEquals("pig eat me", stringHandler.removeFirstMatched("pig eat me","add"));
        assertEquals("pig eat me", stringHandler.removeFirstMatched("pig eat me",null));
        assertEquals(null, stringHandler.removeFirstMatched(null,"add"));
        assertEquals(null, stringHandler.removeFirstMatched(null,null));
        
            
    }

}
