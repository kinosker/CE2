package fileLogger;

import java.io.BufferedWriter;
import java.util.ArrayList;

/**
 * @author TienLong Provides the implementation of the logger (The strategy to
 *         log header/content)
 */
public interface Logger {
	public void logHeader(BufferedWriter writer);

	public void logContent(BufferedWriter writer, ArrayList<String> toDoList);
}
