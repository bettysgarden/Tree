package node.test;

import node.business.NodeManager;
import node.config.GlobalConfig;

public class Main {
    public static void main(String[] args) {
        try {
            GlobalConfig.initGlobalConfig();
            UserInterface.listing();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
