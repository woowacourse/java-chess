package chess.domain.chess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import chess.domain.ConnectionUtils;

public class ChessDAO {

    public void deleteIfPreviousChessExists() throws SQLException {
        Optional<Long> chessId = findChessId();
        if (chessId.isPresent()) {
            deletePreviousPiece(chessId.get());
            deletePreviousChess(chessId.get());
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

    private void deletePreviousPiece(Long chessId) throws SQLException {
        String chessQuery = "DELETE FROM piece WHERE chess_id = (?)";
        deleteById(chessQuery, chessId);
    }

    public void deletePreviousChess(Long chessId) throws SQLException {
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

    public void insert() throws SQLException {
        String query = "INSERT INTO chess (turn) VALUES ('WHITE')";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.executeUpdate();
        }
    }

    public void update(Long chessId) throws SQLException {
        String query = "INSERT INTO chess (turn) VALUES ('WHITE')";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, chessId);
            pstmt.executeUpdate();
        }
    }

    public String findTurnByChessId() throws SQLException {
        Optional<String> turn = Optional.empty();
        String query = "SELECT turn FROM chess c";

        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                turn = Optional.of(resultSet.getString("turn"));
            }
        }
        return turn.orElseThrow(() -> new IllegalStateException("진행 중인 체스 게임이 없습니다."));
    }
}
