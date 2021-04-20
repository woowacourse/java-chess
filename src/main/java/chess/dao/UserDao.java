package chess.dao;

import static chess.dao.utils.ConnectionManager.createConnection;

import chess.dao.exception.UncheckedSQLException;
import chess.dao.utils.JDBCHelper;
import chess.domain.user.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class UserDao implements UserDaoInterface {

    @Override
    public void insert(final String name) {
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

    @Override
    public Optional<User> selectById(final long id) {
        final String query = "SELECT * FROM user WHERE id = ?";
        try (
            final Connection connection = createConnection();
            final PreparedStatement pstmt = JDBCHelper.createPreparedStatement(
                connection, query, Collections.singletonList(id)
            );
            final ResultSet resultSet = pstmt.executeQuery()
        ) {
            return makeUser(resultSet);
        } catch (final SQLException e) {
            throw new UncheckedSQLException(e.getMessage());
        }
    }

    @Override
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

    @Override
    public void updateResult(final long winnerId, final long loserId) {
        final String query = "UPDATE user SET "
            + "win_count = (CASE WHEN id = ? THEN win_count+1 ELSE win_count END), "
            + "lose_count = (CASE WHEN id = ? THEN lose_count+1 ELSE lose_count END) "
            + "WHERE id = ? OR id = ?";
        try (
            final Connection connection = createConnection();
            final PreparedStatement pstmt = JDBCHelper.createPreparedStatement(
                connection, query, Arrays.asList(
                    winnerId, loserId, winnerId, loserId
                )
            )
        ) {
            pstmt.executeUpdate();
        } catch (final SQLException e) {
            throw new UncheckedSQLException(e.getMessage());
        }
    }

    @Override
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
