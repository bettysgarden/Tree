package node.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbDAO {
    private ConnectionBuilder builder = ConnectionBuilderFactory.getConnectionBuilder();

    protected Connection getConnection() throws SQLException {
        return builder.getConnection();
    }
    public void createTable() {
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
        ) {
            String sql = "CREATE TABLE Node " +
                    "(id SERIAL,\n" +
                    "parent_id INT,\n" +
                    "value VARCHAR(50) NOT NULL,\n" +
                    "PRIMARY KEY (id))";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
        ) {
            String sql = "DROP TABLE IF EXISTS Node;\n";
            stmt.executeUpdate(sql);
            System.out.println("Table deleted in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // заполнение начальными данными
    public void batchNodes(){

    }
}
