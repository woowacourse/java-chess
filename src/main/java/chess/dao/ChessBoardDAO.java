package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ChessBoardDAO {
    private static ChessBoardDAO instance = new ChessBoardDAO();
    private Connection connection = getConnection();

    private ChessBoardDAO() {
    }

    public static ChessBoardDAO getInstance() {
        return instance;
    }

    private Connection getConnection() {
        Connection connection;
        String server = "127.0.0.1:13306";
        String database = "Chess";
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("※ JDBC Driver load 오류: " + e.getMessage());
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName,
                                                     password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("※ 연결 오류: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return connection;
    }

    public void closeConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                System.err.println("※ Connection 오류:" + e.getMessage());
            }
        }
    }
}
