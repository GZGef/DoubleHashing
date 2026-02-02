package com.github.gzgef.doublehashing;

/**
 * Реализация хеш-таблицы с использованием двойного хеширования для разрешения коллизий.
 * Этот класс расширяет абстрактный класс Map и предоставляет обобщенную реализацию
 * хеш-таблицы с автоматическим изменением размера.
 *
 * @param <K> тип ключей, поддерживаемых этой хеш-таблицей
 * @param <V> тип отображаемых значений
 */
public class HashMap<K, V> extends Map<K, V> {

    private static final int HASH_PARAM = 47;
    private static final double REHASH_THRESHOLD = 0.75;
    private static int capacity;

    private Node[] table;

    /**
     * Создает пустой HashMap с начальной емкостью по умолчанию (10).
     */
    public HashMap() {
        capacity = 10;
        table = new Node[capacity];
        size = 0;
    }

    /**
     * Перехеширует хеш-таблицу, удваивая ее емкость и повторно вставляя все записи.
     */
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

    /**
     * Первая хеш-функция для двойного хеширования.
     *
     * @param key             ключ для хеширования
     * @param currentCapacity текущая емкость хеш-таблицы
     * @return хеш-значение
     */
    private int hashFunc1(K key, int currentCapacity) {
        return (key.hashCode() & 0x7fffffff) % currentCapacity;
    }

    /**
     * Вторая хеш-функция для двойного хеширования.
     * Обеспечивает нечетный размер шага для лучшего распределения.
     *
     * @param key             ключ для хеширования
     * @param currentCapacity текущая емкость хеш-таблицы
     * @return размер шага для пробирования
     */
    private int hashFunc2(K key, int currentCapacity) {
        int hash = (key.hashCode() * HASH_PARAM) % (currentCapacity - 1);

        if (hash % 2 == 0) {
            ++hash;
        }

        return hash;
    }

    /**
     * Вставляет ключ-значение в хеш-таблицу.
     * Если ключ уже существует, значение обновляется.
     * Автоматически перехеширует, если коэффициент загрузки превышает порог.
     *
     * @param key   ключ для вставки
     * @param value значение для ассоциации с ключом
     */
    @Override
    public void put(K key, V value) {
        if (REHASH_THRESHOLD <= (size * 1.0 / capacity)) {
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

    /**
     * Удаляет запись с указанным ключом из хеш-таблицы.
     *
     * @param key ключ для удаления
     */
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

    /**
     * Возвращает значение, связанное с указанным ключом.
     *
     * @param key ключ, для которого нужно вернуть связанное значение
     * @return значение, связанное с ключом, или null, если ключ не найден
     */
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

    /**
     * Возвращает строковое представление хеш-таблицы.
     *
     * @return строковое представление хеш-таблицы
     */
    @Override
    public String toString() {
        StringBuilder description = new StringBuilder("Hash table: [ ");

        for (int i = 1; i < capacity; i++) {
            Node<K, V> node = table[i];

            if (node != null) {
                description.append(node.getValue())
                           .append(" ");
            }
        }

        return description.append(']').toString();
    }

    /**
     * Внутренний класс, представляющий узел в хеш-таблице.
     * Реализует интерфейс Entry для хранения пар ключ-значение.
     */
    private class Node<K, V> implements Entry<K, V> {
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
