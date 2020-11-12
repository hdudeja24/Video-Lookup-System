public interface HMapInterface{
    boolean containsKey(String key);

    boolean containsValue(Movie value);

    Movie getValue(String key);

    boolean isEmpty();

    Movie removeValue(String key);

    int getSize();

    boolean insert(String key, Movie value);

    void printMap();

}
