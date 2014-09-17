package command;

public class StringHandler {

    /**
     * @param source
     *            the string where the first word is to be extracted
     * @return the extracted first word or null if there is no first word
     */
    public String getFirstWord(String source) {
        if (source != null) {
            source.trim();
            if (!source.isEmpty()) {
                if (source.contains(" ")) {
                    String firstWord = source.substring(0, source.indexOf(" "));
                    return firstWord;
                }
            }
        }
        return null;
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
    public String removeFirstMatched(String source, String toRemove) {
        if (source != null && toRemove != null) {
            String newWord = source.replaceFirst(toRemove, "");
            return newWord;
        }

        return source;
    }
}
