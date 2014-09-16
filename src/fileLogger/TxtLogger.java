package fileLogger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * @author TienLong Implements the logger interface to provide the algorithm of
 *         how the content and header is logged
 */
public class TxtLogger implements Logger {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fileLogger.Logger#logHeader(java.io.BufferedWriter) Not used in CE1
	 */
	@Override
	public void logHeader(BufferedWriter writer) {
		/*
		 * not logging header for this version.... try {
		 * writer.write("testing header"); writer.newLine(); writer.flush(); }
		 * catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fileLogger.Logger#logContent(java.io.BufferedWriter,
	 * java.util.ArrayList) Log toDoList task(s) into the file
	 */
	@Override
	public void logContent(BufferedWriter writer, ArrayList<String> toDoList) {
		// TODO Auto-generated method stub
		ListIterator<String> iterator = toDoList.listIterator();

		try {
			while (iterator.hasNext()) {
				writer.write(iterator.next());
				writer.newLine();
			}

			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
