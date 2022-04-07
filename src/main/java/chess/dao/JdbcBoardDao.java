package chess.dao;

import chess.dto.response.Turn;
import chess.piece.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

public class JdbcBoardDao implements BoardDao {
    private static final String URL = "jdbc:mysql://localhost:3307/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Long save(final Color color) {
        final Connection connection = getConnection();
        final String sql = "insert into board (id, turn) values (NULL, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, color.name());
            statement.executeUpdate();
            final ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (final SQLException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    @Override
    public Turn findById(final Long boardId) {
        final Connection connection = getConnection();
        final String sql = "select turn from board where id = (?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, boardId);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new Turn(resultSet.getString("turn"));
        } catch (final SQLException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(final Long boardId, final Color color) {
        final Connection connection = getConnection();
        final String sql = "update board set turn = (?) where id = (?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, color.name());
            statement.setLong(2, boardId);
            statement.executeUpdate();
        } catch (final SQLException e) {
            logger.warn(e.getMessage());
        }
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (final SQLException e) {
            logger.warn(e.getMessage());
        }
        return connection;
    }
}
