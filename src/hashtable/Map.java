package hashtable;

public abstract class Map<K,V> implements IHashTable<K,V> {
    protected static int size;

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }
}
