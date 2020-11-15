/**
 * @author: Hitanshu Dudeja
 * Class: EECS 2500
 * Project 3: Video-Lookup-System
 * Date : 11/15/2020
 **/

public interface HMapInterface{
    boolean containsKey(String key);

    boolean containsValue(Movie value);

    Movie getValue(String key);

    boolean isEmpty();

    Movie removeValue(String key);

    int getSize();

    boolean insert(String key, Movie value);

    void printMap();

    int getMapArrLen();                     // Returns the length of the array of the hashmap

    int getLastSearchResult();              // Get the total nodes of the last search

}
