package node.business;

import node.dao.DbDAO;
import node.dao.NodeDAO;

public class DbManager {
    private final DbDAO dao;

    public DbManager() {
        dao = new DbDAO();
        dao.dropTable();
        dao.createTable();
    }
}
