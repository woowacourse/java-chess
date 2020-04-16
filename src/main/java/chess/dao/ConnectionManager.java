package chess.dao;

import java.sql.*;

public class ConnectionManager {
    private ConnectionManager() {
    }

    public static Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306";
        String database = "webChess";
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new CustomSQLException(e.getMessage(), e);
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option,
                    userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            throw new CustomSQLException(e.getMessage(), e);
        }

        return con;
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e.getMessage(), e);
        }
    }

    public static void closePreparedStatement(PreparedStatement pstmt) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e.getMessage(), e);
        }
    }
}
