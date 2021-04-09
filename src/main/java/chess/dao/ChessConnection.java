package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ChessConnection {
    ConnectionProperty connectionProperty;

    public ChessConnection(final ConnectionProperty connectionProperty) {
        this.connectionProperty = connectionProperty;
    }

    public final Connection getConnection() {
        Connection con = null;

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con =
                    DriverManager.getConnection("jdbc:mysql://" + connectionProperty.getServer() + "/" + connectionProperty.getDatabase() + connectionProperty.getOption(),
                            connectionProperty.getUserName(), connectionProperty.getPassword());
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return con;
    }

    // 드라이버 연결해제
    public final void closeConnection(final Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }
}
