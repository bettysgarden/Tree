package node.dao;

import node.entity.Node;
import node.exception.NodeDaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class NodeDbDAO extends DbDAO implements NodeDAO {
    private static final String SELECT
            = "SELECT id, parent_id, value FROM \"Node\" ORDER BY parent_id, id, value";
    private static final String INSERT
            = "INSERT INTO \"Node\" (parent_id, value) VALUES (?, ?)";
    private static final String UPDATE
            = "UPDATE \"Node\" SET value=? WHERE id=?";
    private static final String DELETE
            = "DELETE FROM \"Node\" WHERE id=?";


    @Override
    public Long addNode(Node node) throws NodeDaoException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT, new String[]{"id"})) {
            Long nodeId = -1L;
            pst.setLong(1, node.getParentId());
            pst.setString(2, node.getValue());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                nodeId = gk.getLong("id");
            }
            gk.close();
            return nodeId;
        } catch (Exception e) {
            throw new NodeDaoException(e);
        }
    }

    @Override
    public void updateNode(Node node) throws NodeDaoException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setString(1, node.getValue());
            pst.setLong(2, node.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new NodeDaoException(e);
        }
    }

    @Override
    public void deleteNode(Long nodeId) throws NodeDaoException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE)) {
            pst.setLong(1, nodeId);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new NodeDaoException(e);
        }
    }
    @Override
    public List<Node> listNodes() throws NodeDaoException {
//        return null;
        List<Node> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillNode(rs));
            }
            rs.close();
        } catch (Exception e) {
            throw new NodeDaoException(e);
        }
        return list;
    }

    private Node fillNode(ResultSet rs) throws SQLException {
        Node node = new Node();
        node.setId(rs.getLong("id"));
        node.setParentId(rs.getLong("parent_id"));
        node.setValue(rs.getString("value"));
        return node;
    }

    public void addListOfNodes() throws NodeDaoException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(
                     "INSERT INTO \"Node\" (id, parent_id, value) VALUES (?, ?, ?)")) {
            for (int i = 0; i < 10; i++) {
                // Заполняем параметры запроса
                stmt.setString(1, "p" + i);
                stmt.setString(2, "LastNAme_" + i);
                stmt.setString(3, "phone_" + i);
                // Запрос не выполняется, а укладывается в буфер,
                //  который потом выполняется сразу для всех команд
                stmt.addBatch();
            }
// Выполняем все запросы разом
            int[] results = stmt.executeBatch();
        } catch (Exception e) {
            throw new NodeDaoException(e);
        }
    }
}
