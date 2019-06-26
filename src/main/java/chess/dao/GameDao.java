package chess.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDao {
    private static final String SELECT_TURN_BY_ID = "SELECT turn FROM game WHERE game_id = ?";
    private static final String INSERT_GAME = "INSERT INTO game(game_id) SELECT ifnull(MAX(game_id) + 1, 1) FROM game";
    private static final String UPDATE_TURN = "UPDATE game SET turn = ? WHERE game_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM game WHERE game_id = ?";
    private static final String SELECT_MAX_ID = "SELECT MAX(game_id) FROM game";
    private static final String SELECT_ALL_ID = "SELECT game_id FROM game LIMIT 0, 1000";
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

    public int findMaxId() throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            return executeFindMaxId(con);
        }
    }

    private int executeFindMaxId(Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_MAX_ID)) {
            return getMaxId(pstmt);
        }
    }

    private int getMaxId(PreparedStatement pstmt) throws SQLException {
        try (ResultSet rs = pstmt.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        }
    }

    public List<Integer> findAllId() throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            return executeFindAllId(con);
        }
    }

    private List<Integer> executeFindAllId(Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_ALL_ID)) {
            return getAllId(pstmt);
        }
    }

    private List<Integer> getAllId(PreparedStatement pstmt) throws SQLException {
        try (ResultSet rs = pstmt.executeQuery()) {
            List<Integer> gameIds = new ArrayList<>();
            while (rs.next()) {
                gameIds.add(rs.getInt("game_id"));
            }
            return gameIds;
        }
    }
}
