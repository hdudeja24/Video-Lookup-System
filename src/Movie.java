import java.io.*;
import java.util.*;

public class Movie {
    private String title;
    private int yearReleased;
    private int runningTime;

    public Movie() {
        this("", 0, 0);
    }

    public Movie(String title, int yearReleased, int runningTime) {
        this.title = title.trim();
        this.yearReleased = yearReleased;
        this.runningTime = runningTime;
    }

    public Movie(Movie m) {
        title = m.title.trim();
        yearReleased = m.yearReleased;
        runningTime = m.runningTime;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public String getTitle() {
        return title;
    }

    public int[] getFirstLetterCodes() {
        String[] words;
        int[] result;
        words = title.split(" ");
        result = new int[words.length];
        for (int wordNbr = 0; wordNbr < words.length; wordNbr++) {
            if (words.length != 0 && words[wordNbr].length() > 0) {
                result[wordNbr] = Character.valueOf(words[wordNbr].charAt(0));
            }
        }
        return result;
    }

    public int getHashKey() {
        /**
         * Method created a hash value for the title of the Movie object
         * @returns a hash value created by the title of the movie
        * */

        int[] values =  getFirstLetterCodes();      // Get the char values of the 1st letter of each word in the movie's title
        int result = values[0];                     // Result will be returned, we start with the first value in the array
        if(values.length == 1)                      // If the array is only size of 1
            return result;                          // Then we return the result with just the first value in tha array
                                                    // Else the values is size greater than 1
        for(int i = 1; i < values.length; i++)      // We iterate through each value, starting from the 2nd one
            result = (result) * 128 + values[i];    // Multiply the current result times 128 and add the current value of the arrya
        return result;                              // Return the final result
    }

    public String toString() {
        return "\"" + title + "\" Released in : " + yearReleased + " Running Time : " + runningTime + " minutes";
    }
}