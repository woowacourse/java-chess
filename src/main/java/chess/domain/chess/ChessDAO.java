package chess.domain.chess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import chess.domain.ConnectionUtils;
import chess.domain.position.MovePosition;

public class ChessDAO {
    public void deleteIfPreviousChessExists() throws SQLException {
        Optional<Long> chessId = findAnyChessId();
        if (chessId.isPresent()) {
            deletePreviousChess(chessId.get());
        }
    }

    public Optional<Long> findAnyChessId() throws SQLException {
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

    public void deletePreviousChess(Long chessId) throws SQLException {
        removeChess(chessId);
    }

    private void removeChess(Long chessId) throws SQLException {
        String chessQuery = "DELETE FROM chess WHERE chess_id = (?)";
        deleteById(chessQuery, chessId);
    }

    private void deleteById(String query, Long id) throws SQLException {
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }

    public void saveChess() throws SQLException {
        String query = "INSERT INTO chess VALUES ()";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
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
