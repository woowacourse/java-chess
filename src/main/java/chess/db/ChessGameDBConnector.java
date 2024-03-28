package chess.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ChessGameDBConnector {
    private static final String SERVER = "localhost:13306";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private final String database;

    ChessGameDBConnector(String database) {
        this.database = database;
    }

    public ChessGameDBConnector() {
        this("chess");
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + database + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.out.println("DB 연결 오류: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
