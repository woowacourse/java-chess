package chess.persistence.dao;

import chess.persistence.dto.GameSessionDto;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameSessionDao {

    private DataSource dataSource;

    public GameSessionDao(DataSource ds) {
        this.dataSource = ds;
    }

    public long addSession(GameSessionDto sess) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(GameSessionDaoSql.INSERT, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, sess.getTitle());
            query.setString(2, sess.getState());
            return getGeneratedKey(query);
        }
    }

    private long getGeneratedKey(PreparedStatement query) throws SQLException {
        query.executeUpdate();
        try (ResultSet rs = query.getGeneratedKeys()) {
            rs.next();
            return rs.getLong(1);
        }
    }

    public Optional<GameSessionDto> findById(long id) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(GameSessionDaoSql.SELECT_BY_ID)) {
            query.setLong(1, id);
            return handleSingleResult(query);
        }
    }

    private Optional<GameSessionDto> handleSingleResult(PreparedStatement query) throws SQLException {
        try (ResultSet rs = query.executeQuery()) {
            if (!rs.next()) {
                return Optional.empty();
            }
            return Optional.of(mapResult(rs));
        }
    }

    private GameSessionDto mapResult(ResultSet rs) throws SQLException {
        return GameSessionDto.of(
            rs.getLong("id"),
            rs.getString("state"),
            rs.getString("title")
        );
    }

    public Optional<GameSessionDto> findByTitle(String title) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(GameSessionDaoSql.SELECT_BY_TITLE)) {
            query.setString(1, title);
            return handleSingleResult(query);
        }
    }

    public List<GameSessionDto> findLatestSessions(int limit) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(GameSessionDaoSql.SELECT_LATEST_N)) {
            query.setInt(1, limit);
            return handleMultipleResults(query);
        }
    }

    private List<GameSessionDto> handleMultipleResults(PreparedStatement query) throws SQLException {
        try (ResultSet rs = query.executeQuery()) {
            List<GameSessionDto> founds = new ArrayList<>();
            while (rs.next()) {
                founds.add(mapResult(rs));
            }
            return founds;
        }
    }

    public int deleteById(long id) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(GameSessionDaoSql.DELETE_BY_ID)) {
            query.setLong(1, id);
            return query.executeUpdate();
        }
    }

    public int updateSession(GameSessionDto sess) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(GameSessionDaoSql.UPDATE)) {
            query.setString(1, sess.getTitle());
            query.setString(2, sess.getState());
            query.setLong(3, sess.getId());
            return query.executeUpdate();
        }
    }

    private static class GameSessionDaoSql {
        private static final String INSERT = "INSERT INTO game_session(title, state) VALUES(?, ?)";
        private static final String SELECT_BY_ID = "SELECT id, title, state FROM game_session WHERE id=?";
        private static final String SELECT_BY_TITLE = "SELECT id, title, state FROM game_session WHERE title=?";
        private static final String SELECT_LATEST_N = "SELECT id, title, state FROM game_session ORDER BY id DESC LIMIT ?";
        private static final String UPDATE = "UPDATE game_session SET title=?, state=? WHERE id=?";
        private static final String DELETE_BY_ID = "DELETE FROM game_session WHERE id=?";
    }
}
