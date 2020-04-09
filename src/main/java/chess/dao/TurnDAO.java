package chess.dao;

import chess.dto.TurnDTO;

import java.sql.*;

public class TurnDAO {
    private static TurnDAO instance = new TurnDAO();
    private Connection connection = getConnection();

    private TurnDAO() {
    }

    public static TurnDAO getInstance() {
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

    public void saveTurn(TurnDTO turnDTO) {
        String query = "INSERT INTO turn VALUES (?)";
        try (PreparedStatement pstmt = this.connection.prepareStatement(query)) {
            pstmt.setString(1, turnDTO.getCurrentTeam());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePreviousTurn() {
        String query = "DELETE FROM turn";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TurnDTO getTurn() throws SQLException {
        String query = "SELECT * FROM turn";
        try (PreparedStatement pstmt = this.connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (!rs.next()) {
                return null;
            }
            return new TurnDTO(rs.getString("teamName"));
        } catch (SQLException e) {
            throw new SQLException("데이터 베이스에서 SQLException이 발생했습니다.");
        }
    }
}
