package node.business;

import node.dao.NodeDAOFactory;
import node.dao.NodeDAO;
import node.entity.*;
import node.exception.*;

import java.sql.SQLException;
import java.util.*;

/**
 * Класс для реализации функций над списком узлов
 */
public class NodeManager {
    private final NodeDAO dao;

    public NodeManager() {
        dao = NodeDAOFactory.getNodeDAO();
    }

    // Добавление узла - возвращает ID добавленного узла
    public Long addNode(Node Node) throws NodeBusinessException {
        try {
            return dao.addNode(Node);
        } catch (NodeDaoException ex) {
            throw new NodeBusinessException(ex);
        }
    }

    // Редактирование узла -- переименование
    public void updateNode(Node Node) throws NodeBusinessException {
        try {
            dao.updateNode(Node);
        } catch (NodeDaoException ex) {
            throw new NodeBusinessException(ex);
        }
    }

    // Удаление узла по его ID
    public void deleteNode(Long NodeId) throws NodeBusinessException {
        try {
            dao.deleteNode(NodeId);
        } catch (NodeDaoException ex) {
            throw new NodeBusinessException(ex);
        }
    }

    // Отобразить дерево
    public void listNodes() throws NodeBusinessException {
        try {
            dao.listNodes();
        } catch (NodeDaoException ex) {
            throw new NodeBusinessException(ex);
        }

    }

}

