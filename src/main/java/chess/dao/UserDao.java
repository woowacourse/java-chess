package chess.dao;

import static chess.dao.utils.ConnectionManager.createConnection;

import chess.dao.exception.UncheckedSQLException;
import chess.dao.utils.JDBCHelper;
import chess.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;

public class UserDao implements UserDaoInterface {

    public void insertUser(final String name) {
        final String query = "INSERT INTO user(name) VALUES (?)";
        try (
            final Connection connection = createConnection();
            final PreparedStatement pstmt = JDBCHelper.createPreparedStatement(
                connection, query, Collections.singletonList(name)
            )
        ) {
            pstmt.executeUpdate();
        } catch (final SQLException e) {
            throw new UncheckedSQLException(e.getMessage());
        }
    }

    public Optional<User> selectByName(final String name) {
        final String query = "SELECT * FROM user WHERE name = ?";
        try (
            final Connection connection = createConnection();
            final PreparedStatement pstmt = JDBCHelper.createPreparedStatement(
                connection, query, Collections.singletonList(name)
            );
            final ResultSet resultSet = pstmt.executeQuery()
        ) {
            return makeUser(resultSet);
        } catch (final SQLException e) {
            throw new UncheckedSQLException(e.getMessage());
        }
    }

    private Optional<User> makeUser(final ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return Optional.empty();
        }
        return Optional.of(
            new User(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getLong("win_count"),
                resultSet.getLong("lose_count"),
                resultSet.getTimestamp("created_time").toLocalDateTime()
            )
        );
    }

    public void deleteByName(final String name) {
        final String query = "DELETE FROM user WHERE name = ?";
        try (
            final Connection connection = createConnection();
            final PreparedStatement pstm = JDBCHelper.createPreparedStatement(
                connection, query, Collections.singletonList(name)
            )
        ) {
            pstm.executeUpdate();
        } catch (final SQLException e) {
            throw new UncheckedSQLException(e.getMessage());
        }
    }
}
