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
        try (Connection connection = dbManager.getConnection()) {
            String query = "INSERT INTO CHESS_GAME(turn, isFinish) VALUE (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, chessGame.turn().name());
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

    public void saveWithId(int chessGameId, String turn, boolean isFinish) {
        try (Connection connection = dbManager.getConnection()) {
            String query = "INSERT INTO CHESS_GAME VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, chessGameId);
            pstmt.setString(2, turn);
            pstmt.setBoolean(3, isFinish);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> selectAllChessGameId() {
        List<Integer> chessGameIds = new ArrayList<>();
        try (Connection connection = dbManager.getConnection()) {
            String query = "SELECT * FROM CHESS_GAME";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                chessGameIds.add(rs.getInt("chessGameId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chessGameIds;
    }

    public ChessGameStatusDto findChessGameStateById(int chessGameId) {
        try (Connection connection = dbManager.getConnection()) {
            String query = "SELECT turn, IF(isFinish, 'true', 'false') isFinish FROM CHESS_GAME WHERE chessGameId = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, chessGameId);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) return null;
            return new ChessGameStatusDto(rs.getString("turn"), rs.getBoolean("isFinish"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateChessGameStateById(int chessGameId, String turn, boolean isFinish) {
        try (Connection connection = dbManager.getConnection()) {
            String query = "UPDATE CHESS_GAME SET turn = ?, isFinish = ? WHERE chessGameId = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, turn);
            pstmt.setBoolean(2, isFinish);
            pstmt.setInt(3, chessGameId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteChessGameById(int chessGameId) {
        try (Connection connection = dbManager.getConnection()) {
            String query = "DELETE FROM CHESS_GAME WHERE chessGameId = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, chessGameId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
