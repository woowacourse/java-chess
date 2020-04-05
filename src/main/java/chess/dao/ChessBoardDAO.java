package chess.dao;

import chess.dto.BoardDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChessBoardDAO {
    private static final int MIN_BOARD_LINE = 1;
    private static final int MAX_BOARD_LINE = 8;

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

    public void saveBoard(BoardDTO boardDTO) {
        String query = "INSERT INTO chessBoard VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = this.connection.prepareStatement(query)) {
            for (int i = MIN_BOARD_LINE; i <= MAX_BOARD_LINE; i++) {
                pstmt.setString(i, boardDTO.getLines().get(i - 1));
            }
            pstmt.setString(9, boardDTO.getCurrentTeam());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePreviousBoard() {
        String query = "DELETE FROM chessBoard";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
