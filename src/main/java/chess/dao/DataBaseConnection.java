package chess.dao;

import chess.view.OutputView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final Connection connection;

    static {
        connection = createConnection();
    }

    private DataBaseConnection() {
    }

    private static Connection createConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);

        } catch (SQLException e) {
            OutputView.printErrorMessage(e.getMessage());
            return null;
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
