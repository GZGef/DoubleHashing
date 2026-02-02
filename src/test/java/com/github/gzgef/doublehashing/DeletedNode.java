package com.github.gzgef.doublehashing;

/**
 * Синглтон-класс, представляющий удаленный узел в хеш-таблице.
 * Используется для пометки удаленных записей в хеш-таблицах с открытой адресацией.
 */
public final class DeletedNode extends HashTableNode {
    private static DeletedNode node = null;

    /**
     * Приватный конструктор для реализации паттерна синглтон.
     */
    private DeletedNode() {
        super(-1, -1);
    }

    /**
     * Возвращает уникальный экземпляр DeletedNode.
     *
     * @return уникальный экземпляр DeletedNode
     */
    public static DeletedNode getUniqueDeletedNode() {
        if (node == null)
            node = new DeletedNode();
        return node;
    }
}
