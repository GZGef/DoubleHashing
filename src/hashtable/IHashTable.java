package hashtable;

public interface IHashTable<K, V> {

    boolean isEmpty();

    void put(K key, V value);

    void remove(K key);

    V get(K key);

    int getSize();
}
