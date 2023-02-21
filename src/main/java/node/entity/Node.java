package node.entity;

/**
 * Класс для хранения данных дерева
 */
public class Node {
    private Long id;
    private Long parentId;
    private String value;

    public Node() {
    }

    public Node(Long parent, String value) {
        this.value = value;
        this.parentId = parent;
    }

    public Node(Long id, Long parent, String value) {
        this.id = id;
        this.value = value;
        this.parentId = parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "(" + id + ")  " + value;
    }
}

