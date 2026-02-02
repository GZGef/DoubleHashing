package com.github.gzgef.doublehashing;

/**
 * Общий класс узла для реализаций хеш-таблиц.
 *
 * @param <T1> тип ключа
 * @param <T2> тип значения
 */
public class HashTableNode<T1, T2> {
    private T1 key;
    private T2 value;

    /**
     * Создает новый узел хеш-таблицы с указанным ключом и значением.
     *
     * @param key   ключ
     * @param value значение
     */
    HashTableNode(T1 key, T2 value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Возвращает ключ этого узла.
     *
     * @return ключ
     */
    public T1 getKey() {
        return key;
    }

    /**
     * Устанавливает ключ этого узла.
     *
     * @param key новый ключ
     */
    public void setKey(T1 key) {
        this.key = key;
    }

    /**
     * Возвращает значение этого узла.
     *
     * @return значение
     */
    public T2 getValue() {
        return value;
    }

    /**
     * Устанавливает значение этого узла.
     *
     * @param value новое значение
     */
    public void setValue(T2 value) {
        this.value = value;
    }
}
