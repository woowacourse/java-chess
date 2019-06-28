package chess.dao;

import chess.domain.piece.PieceColor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessTurnDao {
    private static final String insertQuery = "INSERT INTO chess_turn (turn) VALUES (?)";
    private static final String updateQuery = "UPDATE chess_turn SET turn=? WHERE game_id=?";
    private static final String selectTurnQuery = "SELECT turn FROM chess_turn WHERE game_id=?";
    private static final String selectMaxQuery = "SELECT max(game_id) FROM chess_turn";
    private static final String deleteQuery = "DELETE FROM chess_turn WHERE game_id=?";
    private static final String selectGameIdsQuery = "SELECT game_id FROM chess_turn";
    private static ChessTurnDao chessTurnDAO;

    private ChessTurnDao() {
    }

    public static ChessTurnDao getInstance() {
        if (chessTurnDAO == null) {
            chessTurnDAO = new ChessTurnDao();
        }
        return chessTurnDAO;
    }

    public PieceColor selectChessTurn(int id) throws SQLException {
        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement pstmt = connection.prepareStatement(selectTurnQuery);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            return rs.next() ? PieceColor.valueOf(rs.getString("turn")) : null;
        }
    }

    public void updateChessTurn(int id, PieceColor turn) throws SQLException {
        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement pstmt = connection.prepareStatement(updateQuery);
            pstmt.setString(1, String.valueOf(turn));
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
    }

    public void insertChessTurn(PieceColor turn) throws SQLException {
        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement pstmt = connection.prepareStatement(insertQuery);
            pstmt.setString(1, String.valueOf(turn));
            pstmt.executeUpdate();
        }
    }

    public int selectMaxGameId() throws SQLException {
        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement pstmt = connection.prepareStatement(selectMaxQuery);
            ResultSet rs = pstmt.executeQuery();

            return rs.next() ? rs.getInt("max(game_id)") : -1;
        }
    }

    public void deleteChessTurn(int id) throws SQLException {
        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement pstmt = connection.prepareStatement(deleteQuery);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public List<Integer> selectChessGames() throws SQLException {
        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement pstmt = connection.prepareStatement(selectGameIdsQuery);
            ResultSet rs = pstmt.executeQuery();

            List<Integer> gameIds = new ArrayList<>();
            while (rs.next()) {
                gameIds.add(rs.getInt("game_id"));
            }

            return gameIds;
        }
    }
}
