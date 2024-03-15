public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt " + index);
        }
        return myWords[index];
    }

    public int length() {
        return myWords.length;
    }

    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (String word : myWords) {
            ret.append(word);
            ret.append(" ");
        }
        return ret.toString().trim();
    }

    public int hashCode() {
        if (myHash == 0) {
            myHash = toString().hashCode();
        }
        return myHash;
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (myWords.length != other.myWords.length) {
            return false;
        }
        for (int i = 0; i < myWords.length; i++) {
            String myWord1 = myWords[i];
            String myWord2 = other.myWords[i];
            if (!myWord1.equals(myWord2)) {
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) {
        WordGram out = new WordGram(myWords, 0, myWords.length);
        for (int i = 1; i < out.myWords.length; i++) {
            String currentElement = out.myWords[i];
            out.myWords[i - 1] = currentElement;
        }
        out.myWords[out.myWords.length - 1] = word;
        return out;
    }

}