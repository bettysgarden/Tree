package node.dao;

import node.config.GlobalConfig;

/**
 * Фабрика для создания экземпляра NodeDAO
 */
public class NodeDAOFactory {
    public static NodeDAO getNodeDAO() {
        try {
            Class dao = Class.forName(GlobalConfig.getProperty("dao.class"));
            return (NodeDAO)dao.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
