package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLExecutor {
    private final Connection connection;

    private SQLExecutor(Connection connection) {
        this.connection = connection;
    }

    public static SQLExecutor getInstance() {
        return new SQLExecutor(MysqlConnector.getConnection());
    }

    public <T> T select(StatementLoader statementLoader, ResultSetSerializer<T> serializer) {
        try (final PreparedStatement statement = statementLoader.create(connection)) {
            return serializer.serialize(statement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("select SQL 실행 에러!");
        }
    }

    public void insert(StatementLoader statementLoader) {
        try (final PreparedStatement statement = statementLoader.create(connection)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Long insertAndGetGeneratedKey(StatementLoader statementLoader) {
        try (final PreparedStatement statement = statementLoader.create(connection)) {
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public void update(StatementLoader statementLoader) {
        try (final PreparedStatement statement = statementLoader.create(connection)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(StatementLoader statementLoader) {
        try (final PreparedStatement statement = statementLoader.create(connection)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
