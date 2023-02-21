package node.test;

import node.business.DbManager;
import node.business.NodeManager;
import node.entity.Node;
import node.exception.NodeBusinessException;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserInterface {
    private static   final Logger logger = Logger.getLogger(UserInterface.class.getName());
    private static final NodeManager nodeManager = new NodeManager();
    //private static final DbManager dbManager = new DbManager();

    public static void listing() {
        System.out.println();
        System.out.println("Пример команд:");
        System.out.println(Commands.list);
        System.out.println(Commands.add + " <ID родителя> <значение добавляемого узла>");
        System.out.println(Commands.delete + " <ID удаляемого>");
        System.out.println(Commands.rename + " <ID узла> <новое значение узла>");
        System.out.println(Commands.exit);
        input();
    }

    private static void input() {
        boolean isEnd = false;
        while (!isEnd) {
            try {
                System.out.print("-> ");
                // считываем команду с экрана
                Scanner console = new Scanner(System.in);
                String string = console.nextLine();
                String[] stringsArray = string.split(" ");
                Commands command = Commands.valueOf(stringsArray[0]);

                //     Long i = Long.parseLong("12"); // перевод строки в целое число

                switch (command) {
                    case list -> {
                        System.out.println(nodeManager.listNodes());
                    }
                    case add -> {
                        Long parentId;
                        if (stringsArray[1] == "-")
                            parentId = null;
                        else
                            parentId = Long.parseLong(stringsArray[1]);
                        String value = stringsArray[2];
                        Node node = new Node(parentId, value);
                        nodeManager.addNode(node);
                        System.out.println(nodeManager.listNodes());
                    }
                    case delete -> {
                        Long delNode = Long.parseLong(stringsArray[1]);
                        nodeManager.deleteNode(delNode);
                        System.out.println(nodeManager.listNodes());
                    }
                    case rename -> {
                        Long updNodeId = Long.parseLong(stringsArray[1]);
                        String newValue = stringsArray[2];
                        Node node = new Node(updNodeId, newValue);
                        nodeManager.updateNode(node);
                        System.out.println(nodeManager.listNodes());
                    }
                    case exit -> isEnd = true;
                }

            } catch (IllegalArgumentException exception) {
                System.out.println("Проверьте корректность введенной команды.");
                logger.log(Level.SEVERE, "Несоответствие команд.");
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("Проверьте корректность введенной команды.");
                logger.log(Level.SEVERE, "Неверное количество аргументов.");
            } catch (NodeBusinessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
