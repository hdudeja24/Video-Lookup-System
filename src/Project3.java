public class Project3 {

    public static void main(String [] arguments) {




    }

    public int hashValue(String Key)
    {
        String words[] = Key.split(" ");            // Get all words from the string
        int hashVal = 0;                                    // Initiate the hash value for the string Key
        for (String word: words)
        {
            int wordVal = word.charAt(word.length()-1);                     // get the valuje of the last letter of the word
            hashVal += wordVal;
        }
        return hashVal;
    }
}

