package chess.dao;

import chess.domain.ChessGame;
import chess.dto.ChessGameStatusDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDao {
    private DBManager dbManager = new DBManager();

    public int save(ChessGame chessGame) {
        int newChessGameId = 0;
        String query = "INSERT INTO chess_game(turn, isFinish) VALUE (?, ?)";
        try (Connection connection = dbManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, chessGame.turn());
            pstmt.setBoolean(2, !chessGame.runnable());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                newChessGameId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newChessGameId;

    }

    public void saveWithId(int chessGameId, ChessGame chessGame) {
        String query = "INSERT INTO chess_game VALUES (?, ?, ?)";
        try (Connection connection = dbManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, chessGameId);
            pstmt.setString(2, chessGame.turn());
            pstmt.setBoolean(3, !chessGame.runnable());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> selectAllId() {
        List<Integer> chessGameIds = new ArrayList<>();
        String query = "SELECT * FROM chess_game";
        try (Connection connection = dbManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                chessGameIds.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chessGameIds;
    }

    public ChessGameStatusDto findChessGameStateById(int chessGameId) {
        String query = "SELECT turn, IF(isFinish, 'true', 'false') isFinish FROM chess_game WHERE id = ?";
        try (Connection connection = dbManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, chessGameId);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) return null;
            return new ChessGameStatusDto(rs.getString("turn"), rs.getBoolean("isFinish"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateChessGameStateById(int chessGameId, ChessGame chessGame) {
        String query = "UPDATE chess_game SET turn = ?, isFinish = ? WHERE id = ?";
        try (Connection connection = dbManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, chessGame.turn());
            pstmt.setBoolean(2, !chessGame.runnable());
            pstmt.setInt(3, chessGameId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int chessGameId) {
        String query = "DELETE FROM chess_game WHERE id = ?";
        try (Connection connection = dbManager.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, chessGameId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
