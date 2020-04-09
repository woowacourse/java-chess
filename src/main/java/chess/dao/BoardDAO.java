package chess.dao;

import chess.dto.BoardDTO;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BoardDAO {
    private static BoardDAO instance = new BoardDAO();
    private Connection connection = getConnection();

    private BoardDAO() {
    }

    public static BoardDAO getInstance() {
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

    public void saveBoard(BoardDTO boardDTO) {
        Map<String, String> board = boardDTO.getBoard();

        String query = "INSERT INTO board VALUES (?, ?)";

        for (String position : board.keySet()) {
            try (PreparedStatement pstmt = this.connection.prepareStatement(query)) {
                pstmt.setString(1, position);
                pstmt.setString(2, board.get(position));

                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deletePreviousBoard() {
        String query = "DELETE FROM board";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BoardDTO getBoard() throws SQLException {
        String query = "SELECT * FROM board";
        try (PreparedStatement pstmt = this.connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            Map<String, String> board = new HashMap<>();
            while (rs.next()) {
                board.put(rs.getString(1), rs.getString(2));
            }
            return new BoardDTO(board);
        } catch (SQLException e) {
            throw new SQLException("데이터 베이스에서 SQLException이 발생했습니다.");
        }
    }
}

