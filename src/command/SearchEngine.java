package command;

import java.util.ArrayList;

public class SearchEngine {
    public ArrayList<String> searchCaseSensitive(ArrayList<String> source,
            String searchInput) {
        ArrayList<String> searchList = new ArrayList<String>();
        if (invalidList(source) || invalidSearchInput(searchInput)) {
            return searchList;
        }

        final int INITIAL_INDEX = 1;
        int index = INITIAL_INDEX;
        searchInput = searchInput.trim().toLowerCase();

        for (String task : source) {

            if (task.toLowerCase().contains(searchInput)) {
                searchList.add(index + ". " + task);
            }
            index++;
        }
        return searchList;
    }

    private boolean invalidList(ArrayList<String> source) {
        return source == null || source.isEmpty();
    }
    
    private boolean invalidSearchInput(String searchInput) {
        return searchInput == null || searchInput.trim().isEmpty();
    }

    public ArrayList<String> search(ArrayList<String> source, String searchInput) {
        ArrayList<String> searchList = new ArrayList<String>();
        if (invalidList(source) || invalidSearchInput(searchInput)) {
            return searchList;
        }

        final int INITIAL_INDEX = 1;
        int index = INITIAL_INDEX;

        for (String task : source) {
            if (task.contains(searchInput)) {
                searchList.add(index + ". " + task);
            }
            index++;
        }
        return searchList;
    }

}
