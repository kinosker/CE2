package fileLogger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author TienLong Deal with parsing of the file content
 */
public class Parser {
    private ArrayList<String> toDoList = null;

    /**
     * @param toDoList
     *            the list that store the task to do
     */
    public Parser(ArrayList<String> toDoList) {
        // TODO Auto-generated constructor stub
        this.toDoList = toDoList;
    }

    /**
     * Read the file and add existing task to the list
     * 
     * @param fileName
     *            the name of the file to be read
     */
    public void readContent(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String lineRead;
            while ((lineRead = reader.readLine()) != null) {
                toDoList.add(lineRead);
            }

            reader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
