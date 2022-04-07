package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

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
            throw new RuntimeException("insert SQL 실행 에러!");
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
            throw new RuntimeException("insert SQL 실행 에러!");
        }
        throw new NoSuchElementException("DB에서 데이터를 찾지 못했습니다.");
    }

    public void update(StatementLoader statementLoader) {
        try (final PreparedStatement statement = statementLoader.create(connection)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("update SQL 실행 에러!");
        }
    }

    public void delete(StatementLoader statementLoader) {
        try (final PreparedStatement statement = statementLoader.create(connection)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("delete SQL 실행 에러!");
        }
    }
}
