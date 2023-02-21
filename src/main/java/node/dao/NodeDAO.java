package node.dao;

import node.entity.*;
import node.exception.NodeDaoException;

import java.sql.SQLException;
import java.util.List;
/**
* DAO — это Data Access Object — объект доступа к данным.
 * Интерфейс для определения функций хранлиза данных
*/


public interface NodeDAO {
    // Добавление узла - возвращает ID добавленного узла
    Long addNode(Node node) throws NodeDaoException;
    // Редактирование узла
    void updateNode(Node node) throws NodeDaoException;
    // Удаление узла по его ID
    void deleteNode(Long nodeId) throws NodeDaoException;
    // Вывод дерева
    List<Node> listNodes() throws NodeDaoException;
//    void createTable() throws SQLException;
//    void dropTable() throws SQLException;




    }