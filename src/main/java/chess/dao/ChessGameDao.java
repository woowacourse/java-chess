package chess.dao;

import chess.util.DatabaseConnection;

import java.sql.*;

public class ChessGameDao {
    private static final int NEW_GAME_TURN = 0;
    private static final int NEW_GAME_ID = 1;
    private static final int BLACK_TURN = -1;

    private ChessGameDao() {
    }

    private static class ChessGameDAOHolder {
        static final ChessGameDao CHESS_GAME_DAO = new ChessGameDao();
    }

    public static ChessGameDao getInstance() {
        return ChessGameDAOHolder.CHESS_GAME_DAO;
    }

    public String findChessGameById(int id) {
        String sql = "SELECT pieces FROM game WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next() ? rs.getString("pieces") : null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int findTurnById(int id) {
        String sql = "SELECT turn FROM game WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                return rs.getInt("turn") != NEW_GAME_TURN ? rs.getInt("turn") : BLACK_TURN;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BLACK_TURN;
    }

    public int findLatestChessGameId() {
        String sql = "SELECT max(id) AS latest_id FROM game";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next() ? rs.getInt("latest_id") : NEW_GAME_ID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return NEW_GAME_ID;
    }

    public int addChessGame(String board, int turn) {
        String sql = "INSERT INTO game (pieces, turn) VALUES (?, ?);";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, board);
            statement.setInt(2, turn);
            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                return (rs.next()) ? rs.getInt(1) : NEW_GAME_ID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return NEW_GAME_ID;
    }

    public void removeChessGameById(int id) {
        String sql = "DELETE FROM game WHERE id > 1 AND id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
