package node.entity;

/**
 * Класс для хранения данных дерева
 */
public class Node {
    private long id;
    private long parentId;
    private String value;

    public Node() {
    }

    public Node(long parent, String value) {
        this.value = value;
        this.parentId = parent;
    }

    public Node(long id, long parent, String value) {
        this.id = id;
        this.value = value;
        this.parentId = parent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return " (" + id + ")  " + value;
    }
}

