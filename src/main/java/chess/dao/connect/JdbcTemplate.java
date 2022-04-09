package chess.dao.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTemplate {

    private final DbConnector dbConnector;

    public JdbcTemplate(final DbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public <T> T executeQuery(final String query, final PsConsumer psConsumer, final RsFunction<T> rsFunction) {
        try (final Connection connection = dbConnector.getConnection()) {
            return executeQueryWithConnection(query, connection, psConsumer, rsFunction);
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private <T> T executeQueryWithConnection(final String query, final Connection connection,
                                             final PsConsumer psConsumer, final RsFunction<T> rsFunction) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            psConsumer.accept(preparedStatement);
            return executeQueryWithPreparedStatement(preparedStatement, rsFunction);
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private <T> T executeQueryWithPreparedStatement(final PreparedStatement preparedStatement,
                                                    final RsFunction<T> rsFunction) {
        try (final ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return rsFunction.apply(resultSet);
            }
            throw new IllegalStateException();
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public <T> T executeQuery(final String query, final RsFunction<T> rsFunction) {
        try (final Connection connection = dbConnector.getConnection()) {
            return executeQueryWithConnection(query, connection, rsFunction);
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private <T> T executeQueryWithConnection(final String query, final Connection connection, final RsFunction<T> rsFunction) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return executeQueryWithPreparedStatement(preparedStatement, rsFunction);
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public void executeQuery(final String query, final PsConsumer psConsumer) {
        try (final Connection connection = dbConnector.getConnection()) {
            executeQueryWithConnection(query, connection, psConsumer);
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private void executeQueryWithConnection(final String query, final Connection connection, final PsConsumer psConsumer) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            psConsumer.accept(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public Long executeInsertQuery(final String query, final PsConsumer psConsumer) {
        try (final Connection connection = dbConnector.getConnection()) {
            return executeInsertQuery(query, connection, psConsumer);
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private Long executeInsertQuery(final String query, final Connection connection, final PsConsumer psConsumer) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            psConsumer.accept(preparedStatement);
            preparedStatement.executeUpdate();
            return extractGeneratedKey(preparedStatement);
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private Long extractGeneratedKey(final PreparedStatement preparedStatement) {
        try (final ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
            if (resultSet.next()) {
                return (long) resultSet.getInt(1);
            }
            throw new IllegalStateException();
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
