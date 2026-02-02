package com.github.gzgef.doublehashing;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация хеш-таблицы с использованием цепочек (chaining) для разрешения коллизий.
 * Этот класс используется для тестирования и реализует интерфейс HashTable.
 */
public class ChainHashTable implements HashTable {
    private static final int START_CAPACITY = 8;
    private static final double REHASH_THRESHOLD = 0.75;
    private int size;
    private int capacity;
    private List<List<Node>> table;

    /**
     * Класс узла для хеш-таблицы с цепочками.
     */
    private static class Node {
        int key;
        int value;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Создает пустую хеш-таблицу с цепочками.
     */
    public ChainHashTable() {
        capacity = START_CAPACITY;
        table = new ArrayList<>(capacity);
        size = 0;
        for (int i = 0; i < capacity; i++) {
            table.add(new ArrayList<>());
        }
    }

    /**
     * Ищет значение, связанное с указанным ключом.
     *
     * @param key ключ для поиска
     * @return значение, связанное с ключом, или null, если не найдено
     */
    @Override
    public Integer search(int key) {
        int hash = key % capacity;
        List<Node> bucket = table.get(hash);
        for (Node node : bucket) {
            if (node.key == key) {
                return node.value;
            }
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
        if (REHASH_THRESHOLD <= (size * 1.0 / capacity)) {
            rehash();
        }
        int hash = key % capacity;
        List<Node> bucket = table.get(hash);
        for (Node node : bucket) {
            if (node.key == key) {
                node.value = value;
                return;
            }
        }
        bucket.add(new Node(key, value));
        ++size;
    }

    /**
     * Перехеширует хеш-таблицу, удваивая ее емкость и повторно вставляя все записи.
     */
    private void rehash() {
        final int newCapacity = capacity * 2;
        final List<List<Node>> newTable = new ArrayList<>(newCapacity);
        for (int i = 0; i < newCapacity; i++) {
            newTable.add(new ArrayList<>());
        }
        for (int i = 0; i < capacity; i++) {
            List<Node> bucket = table.get(i);
            for (Node node : bucket) {
                int hash = node.key % newCapacity;
                newTable.get(hash).add(node);
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
        int hash = key % capacity;
        List<Node> bucket = table.get(hash);
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).key == key) {
                bucket.remove(i);
                --size;
                return;
            }
        }
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
        for (int i = 0; i < capacity; i++) {
            List<Node> bucket = table.get(i);
            for (Node node : bucket) {
                if (node.key <= min) {
                    min = node.key;
                    result = node.value;
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
        for (int i = 0; i < capacity; i++) {
            List<Node> bucket = table.get(i);
            for (Node node : bucket) {
                if (node.key >= max) {
                    max = node.key;
                    result = node.value;
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
            List<Node> bucket = table.get(i);
            if (bucket.isEmpty()) {
                description.append("__  ");
            } else {
                for (Node node : bucket) {
                    description.append(String.format("%d  ", node.value));
                }
            }
        }
        description.append(']');
        return description.toString();
    }
}
