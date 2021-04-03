package chess.domain.chess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import chess.domain.ConnectionUtils;
import chess.domain.position.MovePosition;

public class ChessDAO {
    public void saveChess(Long userId) throws SQLException {
        addChessAt(userId);
    }

    public void deletePreviousChess(Long chessId) throws SQLException {
        removeMove(chessId);
        removeChess(chessId);
    }

    private void removeMove(Long chessId) throws SQLException {
        String moveQuery = "DELETE FROM move WHERE chess_id = (?)";
        deleteById(moveQuery, chessId);
    }

    private void removeChess(Long chessId) throws SQLException {
        String chessQuery = "DELETE FROM chess WHERE chess_id = (?)";
        deleteById(chessQuery, chessId);
    }

    public Optional<Long> findIdByUserId(Long userId) throws SQLException {
        String query = "SELECT chess_id FROM chess c JOIN user u on u.user_id = c.user_id WHERE u.user_id = (?)";

        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, userId);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return Optional.of(resultSet.getLong("chess_id"));
            }
            return Optional.empty();
        }
    }

    private void deleteById(String query, Long id) throws SQLException {
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }

    private void addChessAt(Long userId) throws SQLException {
        String query =
                "INSERT INTO chess (user_id) VALUES (?)";

        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, userId);
            pstmt.executeUpdate();
        }
    }

    public Chess getChess(Long chessId, Chess chess) throws SQLException {
        String query =
                "SELECT m.source, m.target " +
                        "FROM move m " +
                        "JOIN chess c ON m.chess_id = c.chess_id " +
                        "WHERE c.chess_id = (?)";

        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, chessId);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String source = resultSet.getString("source");
                String target = resultSet.getString("target");
                MovePosition movePosition = new MovePosition(source, target);
                chess = chess.move(movePosition);
            }
        }
        return chess;
    }
}
