package hashtable;

public class HashMap<K,V> extends Map<K,V> {

    private static final int HASH_PARAM = 47;

    private static final double REHASH = 0.75;

    private static int capacity;

    private Node[] table;

    public HashMap() {
        capacity = 10;
        table = new Node[capacity];
        size = 0;
    }

    private void rehash() {
        int newCapacity = capacity * 2;
        Node[] newTable = new Node[newCapacity];

        for (int i = 0; i < capacity; ++i) {
            if (table[i] != null) {
                int hash1 = hashFunc1((K) table[i].getKey(), newCapacity);
                int hash2 = hashFunc2((K) table[i].getKey(), newCapacity);

                if (newTable[hash1] != null) {
                    hash1 = hash2;
                }

                newTable[hash1] = new Node<>(table[i].getKey(), table[i].getValue());
            }
        }

        capacity = newCapacity;
        table = newTable;
    }

    private int hashFunc1(K key, int currentCapacity) {
        return (key.hashCode() & 0x7fffffff) % currentCapacity;
    }

    private int hashFunc2(K key, int currentCapacity) {
        int hash = (key.hashCode() * HASH_PARAM) % (currentCapacity - 1);

        if (hash % 2 == 0) {
            ++hash;
        }

        return hash;
    }

    @Override
    public void put(K key, V value) {
        if (REHASH <= (size * 1.0 / capacity)) {
            rehash();
        }

        int hash1 = hashFunc1(key, capacity);
        int hash2 = hashFunc2(key, capacity);

        while (table[hash1] != null) {
            if (key.equals(table[hash1].getKey())) {
                table[hash1].setValue(value);
                return;
            }

            hash1 = hash2;
        }

        table[hash1] = new Node<>(key, value);
        ++size;
    }

    @Override
    public void remove(K key) {
        int hash1 = hashFunc1(key, capacity);
        int hash2 = hashFunc2(key, capacity);

        while (table[hash1] != null) {
            if (key.equals(table[hash1].getKey())) {
                table[hash1] = null;
                --size;
                return;
            }

            hash1 = hash2;
        }
    }

    @Override
    public V get(K key) {
        int hash1 = hashFunc1(key, capacity);
        int hash2 = hashFunc2(key, capacity);

        while (table[hash1] != null) {
            if (key.equals(table[hash1].getKey())) {
                return (V) table[hash1].getValue();
            }

            hash1 = hash2;
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder("Hash table: [ ");

        for (int i = 1; i < capacity; i++) {
            Node<K,V> node = table[i];

            if (node != null) {
                description.append(node.getValue())
                           .append(" ");
            }
        }

        return description.append(']').toString();
    }

    private class Node<K,V> implements Entry<K,V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V newValue) {
            value = newValue;
        }
    }
}