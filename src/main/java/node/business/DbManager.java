package node.business;

import node.dao.DbDAO;
import node.dao.NodeDAO;
import node.exception.NodeBusinessException;
import node.exception.NodeDaoException;

import java.sql.SQLException;

public class DbManager {
    private final DbDAO dao;

    public DbManager() {
        dao = new DbDAO();
    }
    public void dropTable() throws NodeBusinessException {
        try {
            dao.dropTable();
        } catch (SQLException ex) {
            throw new NodeBusinessException(ex);
        }
    }
    public void createTable() throws NodeBusinessException {
        try {
            dao.createTable();
        } catch (SQLException ex) {
            throw new NodeBusinessException(ex);
        }
    }
    public void batchNodes() throws NodeBusinessException {
        try {
            dao.batchNodes();
        } catch (NodeDaoException ex) {
            throw new NodeBusinessException(ex);
        }
    }
}
