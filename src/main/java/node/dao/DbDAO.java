package node.dao;

import node.entity.Node;
import node.exception.NodeDaoException;

import java.nio.charset.Charset;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DbDAO {
    private ConnectionBuilder builder = ConnectionBuilderFactory.getConnectionBuilder();
    private static final String INSERT
            = "INSERT INTO \"Node\" (parent_id, value) VALUES (?, ?)";

    protected Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    public void createTable() throws SQLException {
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
        ) {
            String sql = "CREATE TABLE \"Node\" " +
                    "(id SERIAL,\n" +
                    "parent_id INT,\n" +
                    "value VARCHAR(50) NOT NULL,\n" +
                    "PRIMARY KEY (id),\n" +
                    "FOREIGN KEY (parent_id) REFERENCES \"Node\"(id) ON DELETE CASCADE)";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new SQLException(e);

        }
    }

    public void dropTable() throws SQLException {
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
        ) {
            String sql = "DROP TABLE IF EXISTS \"Node\";\n";
            stmt.executeUpdate(sql);
            System.out.println("Table deleted in given database...");
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new SQLException(e);
        }
    }

    // заполнение начальными данными
    public void batchNodes() throws NodeDaoException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT, new String[]{"id"})) {
            List<Long> parentId = new ArrayList<>(); // копим список потенциальных родителей
            parentId.add(null);
            parentId.add(null);
            Long nodeId = -1L;

            for (int i = 0; i < 10; i++) {
                // Заполняем параметры запроса
                if (parentId.get(i) == null) {
                    stmt.setNull(1, Types.INTEGER);
                } else {
                    stmt.setLong(1, parentId.get(i));
                }
                stmt.setString(2, generateValue(i + 2));
                // Запрос не выполняется, а укладывается в буфер,
                //  который потом выполняется сразу для всех команд
                stmt.executeUpdate();
                ResultSet gk = stmt.getGeneratedKeys();
                if (gk.next()) {
                    nodeId = gk.getLong("id");
                }
                gk.close();
                parentId.add(nodeId);
                if (nodeId % 2 == 0)
                    parentId.add(nodeId);

                //stmt.addBatch();
            }
            // Выполняем все запросы разом
            //int[] results = stmt.executeBatch();
        } catch (Exception e) {
            throw new NodeDaoException(e);
        }
    }

    public static String generateValue(int len) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
