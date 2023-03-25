package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessBoardAutoIncrementIdGetter {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static String getId() {
        try (Connection connection = getConnection()) {
            PreparedStatement lastInsertIdSelect = connection.prepareStatement("SELECT IFNULL(MAX(id), 0) + 1 AS id FROM chess_board");
            ResultSet resultSet = lastInsertIdSelect.executeQuery();
            String lastInsertId = null;

            if (resultSet.next()) {
                lastInsertId = resultSet.getString("id");
            }

            return lastInsertId;
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

}
