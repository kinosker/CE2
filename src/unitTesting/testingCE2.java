package unitTesting;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import command.CommandHandler;
import client.ConsolePrinter;

public class testingCE2 {

    @Test
    public void test() {
        ArrayList<String> inputList = new ArrayList<String>();
        ArrayList<String> expectedList = new ArrayList<String>();
        
        ByteArrayInputStream in = new ByteArrayInputStream("add abc".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        CommandHandler commandHandler = new CommandHandler(new ConsolePrinter(), scanner, inputList);
        

        expectedList.add("abc");
        commandHandler.executeCommand(scanner.next());
        //commandHandler.executeCommand("clear");
        assertEquals(expectedList, commandHandler.getList());
    }
}
