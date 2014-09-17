package unitTesting;

import static org.junit.Assert.*;

import org.junit.Test;

import command.StringHandler;

public class TestStringHandler {

    @Test
    public void test() {
        StringHandler stringHandler = new StringHandler();
        assertEquals("add", stringHandler.getFirstWord("add Pizza"));
        assertEquals("Pizza", stringHandler.removeFirstMatched("add Pizza", "add"));
        
    }

}
