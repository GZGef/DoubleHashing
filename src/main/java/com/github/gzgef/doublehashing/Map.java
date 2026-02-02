package com.github.gzgef.doublehashing;

/**
 * Абстрактный базовый класс для реализаций хеш-таблиц.
 * Предоставляет общую функциональность для отслеживания размера хеш-таблицы.
 *
 * @param <K> тип ключей, поддерживаемых этой хеш-таблицей
 * @param <V> тип отображаемых значений
 */
public abstract class Map<K, V> implements IHashTable<K, V> {
    protected static int size;

    /**
     * Проверяет, пуста ли хеш-таблица.
     *
     * @return true, если хеш-таблица не содержит записей, иначе false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Возвращает количество записей в хеш-таблице.
     *
     * @return размер хеш-таблицы
     */
    @Override
    public int getSize() {
        return size;
    }
}
