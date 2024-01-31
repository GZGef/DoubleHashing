package hashtable;

public interface Entry<K, V> {
    K getKey();

    V getValue();

    void setValue(V newValue);
}
