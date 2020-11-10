public interface HMapInterface<Key, Value> {
    boolean containsKey(Key key);

    boolean containsValue(Value value);

    Value getValue(Key key);

    boolean isEmpty();

    Value removeValue(Key key);

    int size();

    boolean insert(Key key, Value value);



}
