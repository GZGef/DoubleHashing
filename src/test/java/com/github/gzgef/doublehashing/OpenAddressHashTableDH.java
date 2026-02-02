package com.github.gzgef.doublehashing;

/**
 * Реализация хеш-таблицы с использованием двойного хеширования для разрешения коллизий.
 * Этот класс используется для тестирования и реализует интерфейс HashTable.
 */
@SuppressWarnings("MissortedModifiers")
public class OpenAddressHashTableDH implements HashTable {
    private static final int START_CAPACITY = 8;
    private static final double REHASH = 0.75;
    private static final int HASH_PARAM = 47;
    private int size;
    private int capacity;
    HashTableNode[] table;

    /**
     * Создает пустую хеш-таблицу с двойным хешированием.
     */
    public OpenAddressHashTableDH() {
        capacity = START_CAPACITY;
        table = new HashTableNode[capacity];
        size = 0;
        for (int i = 0; i < capacity; i++)
            table[i] = null;
    }

    /**
     * Ищет значение, связанное с указанным ключом.
     *
     * @param key ключ для поиска
     * @return значение, связанное с ключом, или null, если не найдено
     */
    @Override
    public Integer search(int key) {
        int hash = hashFunc1(key, capacity);
        final int stepSize = hashFunc2(key, capacity);
        while (table[hash] != null) {
            HashTableNode<Integer, Integer> node = (HashTableNode<Integer, Integer>) table[hash];
            if (node.getKey() == key) {
                return node.getValue();
            }
            hash += stepSize;
            hash %= capacity;
        }
        return null;
    }

    /**
     * Добавляет ключ-значение в хеш-таблицу.
     *
     * @param key   ключ
     * @param value значение
     */
    @Override
    public void add(int key, int value) {
        if (REHASH <= (size * 1.0 / capacity)) {
            rehash();
        }
        int hash = hashFunc1(key, capacity);
        final int stepSize = hashFunc2(key, capacity);
        while (table[hash] != null && !table[hash].equals(DeletedNode.getUniqueDeletedNode())) {
            HashTableNode<Integer, Integer> node = (HashTableNode<Integer, Integer>) table[hash];
            if (node.getKey() == key) {
                node.setValue(value);
                return;
            }
            hash += stepSize;
            hash %= capacity;
        }
        table[hash] = new HashTableNode<>(key, value);
        ++size;
    }

    /**
     * Перехеширует хеш-таблицу, удваивая ее емкость и повторно вставляя все записи.
     */
    private void rehash() {
        final int newCapacity = capacity * 2;
        final HashTableNode[] newTable = new HashTableNode[newCapacity];
        for (int i = 0; i < capacity; ++i) {
            if (table[i] != null && !table[i].equals(DeletedNode.getUniqueDeletedNode())) {
                HashTableNode<Integer, Integer> node = (HashTableNode<Integer, Integer>) table[i];
                int hash = hashFunc1(node.getKey(), newCapacity);
                final int stepSize = hashFunc2(node.getKey(), newCapacity);
                while (newTable[hash] != null) {
                    hash += stepSize;
                    hash %= newCapacity;
                }
                newTable[hash] = new HashTableNode<>(node.getKey(), node.getValue());
            }
        }
        capacity = newCapacity;
        table = newTable;
    }

    /**
     * Удаляет запись с указанным ключом из хеш-таблицы.
     *
     * @param key ключ для удаления
     */
    @Override
    public void delete(int key) {
        int hash = hashFunc1(key, capacity);
        final int stepSize = hashFunc2(key, capacity);
        while (table[hash] != null) {
            HashTableNode<Integer, Integer> node = (HashTableNode<Integer, Integer>) table[hash];
            if (node.getKey() == key) {
                table[hash] = DeletedNode.getUniqueDeletedNode();
                --size;
                return;
            }
            hash += stepSize;
            hash %= capacity;
        }
    }

    /**
     * Первая хеш-функция для двойного хеширования.
     *
     * @param key             ключ для хеширования
     * @param currentCapacity текущая емкость хеш-таблицы
     * @return хеш-значение
     */
    public int hashFunc1(int key, int currentCapacity) {
        return key % currentCapacity;
    }

    /**
     * Вторая хеш-функция для двойного хеширования.
     * Обеспечивает нечетный размер шага для лучшего распределения.
     *
     * @param key             ключ для хеширования
     * @param currentCapacity текущая емкость хеш-таблицы
     * @return размер шага для пробирования
     */
    public int hashFunc2(int key, int currentCapacity) {
        int hash = (key * HASH_PARAM) % (currentCapacity - 1);
        if (hash % 2 == 0) {
            ++hash;
        }
        return hash;
    }

    /**
     * Проверяет, пуста ли хеш-таблица.
     *
     * @return true, если хеш-таблица пуста, иначе false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Возвращает минимальный ключ в хеш-таблице.
     *
     * @return минимальный ключ, или null, если хеш-таблица пуста
     */
    @Override
    public Integer min() {
        if (isEmpty()) {
            return null;
        }
        int min = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < capacity; ++i) {
            if (table[i] != null && !table[i].equals(DeletedNode.getUniqueDeletedNode())) {
                HashTableNode<Integer, Integer> node = (HashTableNode<Integer, Integer>) table[i];
                if (node.getKey() <= min) {
                    min = node.getKey();
                    result = node.getValue();
                }
            }
        }
        return result;
    }

    /**
     * Возвращает максимальный ключ в хеш-таблице.
     *
     * @return максимальный ключ, или null, если хеш-таблица пуста
     */
    @Override
    public Integer max() {
        if (isEmpty()) {
            return null;
        }
        int max = Integer.MIN_VALUE;
        int result = 0;
        for (int i = 0; i < capacity; ++i) {
            if (table[i] != null && !table[i].equals(DeletedNode.getUniqueDeletedNode())) {
                HashTableNode<Integer, Integer> node = (HashTableNode<Integer, Integer>) table[i];
                if (node.getKey() >= max) {
                    max = node.getKey();
                    result = node.getValue();
                }
            }
        }
        return result;
    }

    /**
     * Возвращает строковое представление хеш-таблицы.
     *
     * @return строковое представление хеш-таблицы
     */
    @Override
    public String print() {
        final StringBuilder description = new StringBuilder("Hash table: [ ");
        for (int i = 0; i < capacity; i++) {
            if (table[i] == null) {
                description.append("__  ");
            } else if (table[i].equals(DeletedNode.getUniqueDeletedNode())) {
                description.append("D ");
            } else {
                HashTableNode<Integer, Integer> node = (HashTableNode<Integer, Integer>) table[i];
                description.append(String.format("%d  ", node.getValue()));
            }
        }
        description.append(']');
        return description.toString();
    }
}
