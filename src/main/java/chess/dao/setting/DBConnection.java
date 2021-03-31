package chess.dao.setting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String SERVER = "localhost:3306"; // MySQL 서버 주소
    private static final String DATABASE = "chess_game"; // MySQL DATABASE 이름
    private static final String OPTION = "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String USER_NAME = "inbi"; //  MySQL 서버 아이디
    private static final String PASSWORD = "1234"; // MySQL 서버 비밀번호
    private static Connection CONNECTION;

    static {
        loadJDBCDriver();
        connectDriverManager();
    }

    private static void loadJDBCDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void connectDriverManager() {
        try {
            CONNECTION = DriverManager.getConnection(
                "jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return CONNECTION;
    }
}
