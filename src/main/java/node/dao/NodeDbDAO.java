package node.dao;

import node.entity.Node;
import node.exception.NodeDaoException;

import java.sql.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class NodeDbDAO extends DbDAO implements NodeDAO {
    private static final String SELECT
            = "SELECT id, parent_id, value FROM \"Node\" WHERE parent_id=?";
    private static final String SELECT_IS_NULL
            = "SELECT id, parent_id, value FROM \"Node\" WHERE parent_id IS NULL";
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
            if (node.getParentId() == null) {
                pst.setNull(1, Types.INTEGER);
            } else {
                pst.setLong(1, node.getParentId());
            }
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
//    @Override
//    public List<Node> listNodes() throws NodeDaoException {
////        return null;
//        List<Node> list = new LinkedList<>();
//        try (Connection con = getConnection();
//             PreparedStatement pst = con.prepareStatement(SELECT);
//             ResultSet rs = pst.executeQuery()) {
//            while (rs.next()) {
//                list.add(fillNode(rs));
//            }
//            rs.close();
//        } catch (Exception e) {
//            throw new NodeDaoException(e);
//        }
//        return list;
//    }
    @Override
    public List<Node> listNodes() throws NodeDaoException {
//        return null;
        List<Node> list = new LinkedList<>();
        try (Connection con = getConnection()) {

            Set<Long> parentIdSet = new HashSet<>();
            parentIdSet.add(null);
            Set<Long> temp = new HashSet<>();
            PreparedStatement pst;
            String level = "+++ ";
            int count = 0;

            while (!parentIdSet.isEmpty()){
                for (Long parentId :
                        parentIdSet) {
                    if (parentId == null) {
                        pst = con.prepareStatement(SELECT_IS_NULL);
                    } else {
                        pst = con.prepareStatement(SELECT);
                        pst.setLong(1, parentId);
                    }
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        Node node = fillNode(rs);
                        System.out.println();
                        for (int i = 0; i < count; i++) {
                            System.out.print(level);
                        }
                        System.out.print(node.toString());
                        temp.add(node.getId());
                    }
                    rs.close();
                }
                parentIdSet.clear();
                parentIdSet.addAll(temp);
                temp.clear();
                count++;
            }

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
}
