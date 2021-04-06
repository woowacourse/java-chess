package chess.domain.chess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import chess.domain.ConnectionUtils;

public class ChessDAO {

    public void insert() throws SQLException {
        String query = "INSERT INTO chess (turn) VALUES ('WHITE')";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.executeUpdate();
        }
    }

    public void updateTurn(Long chessId) throws SQLException {
        String query =
                "UPDATE chess SET turn = IF(turn = 'WHITE', 'BLACK', 'WHITE') WHERE chess_id = (?);";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, chessId);
            pstmt.executeUpdate();
        }
    }

    public void delete(Long chessId) throws SQLException {
        String query = "DELETE FROM chess WHERE chess_id = (?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, chessId);
            pstmt.executeUpdate();
        }
    }

    public Optional<Long> findChessId() throws SQLException {
        String query = "SELECT chess_id FROM chess c";

        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return Optional.of(resultSet.getLong("chess_id"));
            }
        }
        return Optional.empty();
    }

    public Optional<String> findTurnById(Long chessId) throws SQLException {
        String query = "SELECT c.turn FROM chess c WHERE c.chess_id = (?)";

        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, chessId);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return Optional.of(resultSet.getString("turn"));
            }
        }
        return Optional.empty();
    }
}
