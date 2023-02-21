package node.dao;

public class ConnectionBuilderFactory {
    public static ConnectionBuilder getConnectionBuilder() {
        return new ComboConnectionBuilder();
    }
}
