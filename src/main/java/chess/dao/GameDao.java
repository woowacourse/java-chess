package chess.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDao {
    private static final String SELECT_TURN_BY_ID = "SELECT turn FROM game WHERE game_id = ?";
    private static final String INSERT_GAME = "INSERT INTO game(game_id) SELECT ifnull(MAX(game_id) + 1, 1) FROM game";
    public static final String UPDATE_TURN = "UPDATE game SET turn = ? WHERE game_id = ?";
    public static final String DELETE_BY_ID = "DELETE FROM game WHERE game_id = ?";
    private final DataSource dataSource;

    public GameDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean findTurnByGameId(int gameId) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            return executeFindTurnByGameId(con, gameId);
        }
    }

    private boolean executeFindTurnByGameId(Connection con, int gameId) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_TURN_BY_ID)) {
            pstmt.setInt(1, gameId);
            return getTurnByGameId(pstmt);
        }
    }

    private boolean getTurnByGameId(PreparedStatement pstmt) throws SQLException {
        try (ResultSet rs = pstmt.executeQuery()) {
            rs.next();
            return rs.getBoolean("turn");
        }
    }

    public void add() throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            executeAdd(con);
        }
    }

    private void executeAdd(Connection con) throws SQLException{
        try (PreparedStatement pstmt = con.prepareStatement(INSERT_GAME)) {
            pstmt.executeUpdate();
        }
    }

    public void toggleTurnById(int gameId) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            boolean turn = findTurnByGameId(gameId);
            executeToggle(con, gameId, !turn);
        }
    }

    private void executeToggle(Connection con, int gameId, boolean turn) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_TURN)) {
            pstmt.setBoolean(1, turn);
            pstmt.setInt(2, gameId);
            pstmt.executeUpdate();
        }
    }

    public void deleteById(int gameId) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            executeDeleteById(con, gameId);
        }
    }

    private void executeDeleteById(Connection con, int gameId) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE_BY_ID)) {
            pstmt.setInt(1, gameId);
            pstmt.executeUpdate();
        }
    }
}
