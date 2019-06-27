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
    private static GameDao gameDao;
    private final DataSource dataSource;

    private GameDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static GameDao getInstance(DataSource dataSource) {
        if (gameDao == null) {
            gameDao = new GameDao(dataSource);
        }
        return gameDao;
    }

    public boolean findTurnByGameId(int gameId) {
        try (Connection conn = dataSource.getConnection()) {
            return executeFindTurnByGameId(conn, gameId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean executeFindTurnByGameId(Connection conn, int gameId) {
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_TURN_BY_ID)) {
            stmt.setInt(1, gameId);
            return getTurnByGameId(stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean getTurnByGameId(PreparedStatement stmt) {
        try (ResultSet rs = stmt.executeQuery()) {
            rs.next();
            return rs.getBoolean("turn");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createNewGame() {
        try (Connection conn = dataSource.getConnection()) {
            executeAdd(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeAdd(Connection conn) {
        try (PreparedStatement stmt = conn.prepareStatement(INSERT_GAME)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void toggleTurnById(int gameId) {
        try (Connection conn = dataSource.getConnection()) {
            boolean turn = findTurnByGameId(gameId);
            executeToggle(conn, gameId, !turn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeToggle(Connection conn, int gameId, boolean turn) {
        try (PreparedStatement stmt = conn.prepareStatement(UPDATE_TURN)) {
            stmt.setBoolean(1, turn);
            stmt.setInt(2, gameId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int gameId) {
        try (Connection conn = dataSource.getConnection()) {
            executeDeleteById(conn, gameId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeDeleteById(Connection conn, int gameId) {
        try (PreparedStatement stmt = conn.prepareStatement(DELETE_BY_ID)) {
            stmt.setInt(1, gameId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int findMaxId() {
        try (Connection conn = dataSource.getConnection()) {
            return executeFindMaxId(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int executeFindMaxId(Connection conn) {
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_MAX_ID)) {
            return getMaxId(stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getMaxId(PreparedStatement stmt) {
        try (ResultSet rs = stmt.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> findAllId() {
        try (Connection conn = dataSource.getConnection()) {
            return executeFindAllId(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Integer> executeFindAllId(Connection conn) {
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_ID)) {
            return getAllId(stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Integer> getAllId(PreparedStatement stmt) {
        try (ResultSet rs = stmt.executeQuery()) {
            List<Integer> gameIds = new ArrayList<>();
            while (rs.next()) {
                gameIds.add(rs.getInt("game_id"));
            }
            return gameIds;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
