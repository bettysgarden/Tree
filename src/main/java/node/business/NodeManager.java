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
//            List<Node> nodes = dao.listNodes();
//            HashMap<Long, Set<Node>> parentChildren = new HashMap<>();
//            nodes.forEach(node -> {
//                        if (parentChildren.containsKey(node.getParentId()))
//                            parentChildren.get(node.getParentId()).add(node);
//                        else {
//                            Set<Node> childrenSet = new HashSet<>();
//                            childrenSet.add(node);
//                            parentChildren.put(node.getParentId(), childrenSet);
//                        }
//                    });
//            Set<Long> childrenSet = new HashSet<>();
//            // childrenSet.add(null);
//            Set<Node> set = new HashSet<>(parentChildren.get(null));
//            set.forEach(node -> {
//                System.out.println(node.toString());
//                childrenSet.add(node.getId());
//            }
//            );

//            parentChildren.entrySet().stream().forEach(pair -> {
//                if (childrenSet.contains(pair.getKey())) {
////                    parentChildren.entrySet().stream().forEach(pairIn -> {
////
////                    });
//                    pair.getValue().forEach(node -> System.out.println(node.toString()));
//                }
//            });


            dao.listNodes();
        } catch (NodeDaoException ex) {
            throw new NodeBusinessException(ex);
        }

    }

}

