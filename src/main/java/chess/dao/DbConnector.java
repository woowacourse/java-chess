package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static final String server = "localhost";
    private static final String database = "chess"; // MySQL DATABASE 이름
    private static final String userName = "root"; //  MySQL 서버 아이디
    private static final String password = "*#ryu444081"; // MySQL 서버 비밀번호


    static Connection connect(Connection connection) throws SQLException {
        return DriverManager
                .getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", userName, password);

    }

    static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
