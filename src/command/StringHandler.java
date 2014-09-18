package command;

public class StringHandler {

    /**
     * @param source
     *            the string where the first word is to be extracted
     * @return the extracted first word or null if there is no first word
     */
    public static String getFirstWord(String source) {
        if (source == null) {
            return null;
        }

        source = source.trim();
        
        if (source.isEmpty())
        {
            return null;
        }
        
        if (source.contains(" ")) {
            String firstWord = source.substring(0, source.indexOf(" "));
            return firstWord;
        } 
        else 
        {
            return source;
        }

    }

    /**
     * @param source
     *            the source that require a particular word to be removed
     * @param toRemove
     *            the String that is to be removed
     * @return the new source without the toRemove word
     *         <p>
     *         This function remove the first word that matches with the
     *         toRemove word
     *         <p>
     */
    public static String removeFirstMatched(String source, String toRemove) {
        if (source != null && toRemove != null) {
            toRemove = toRemove.trim();
            String newWord = source.replaceFirst(toRemove, "");
            return newWord.trim();
        }

        return source;
    }
}
