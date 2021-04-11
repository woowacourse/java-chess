package chess.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DBConnection {
    private static final String server = "root@localhost";
    private static final String database = "chessdb";
    private static final String userName = "root";
    private static final String password = "root";

    private static DBConnection instance;

    private DBConnection() {
    }

    public static DBConnection getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection connection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?allowPublicKeyRetrieval=true&serverTimezone=UTC", userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return con;
    }
}
