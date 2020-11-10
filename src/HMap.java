public class HMap<Key , Value> implements HMapInterface<Key, Value>
{
    private int size;                       // Keep track of elements in the HashMap
    @Override
    public boolean containsKey(Key key) {
        return false;
    }

    @Override
    public boolean containsValue(Value value) {
        return false;
    }

    @Override
    public Value getValue(Key key) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Value removeValue(Key key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean insert(Key key, Value value) {
        return false;
    }
}
