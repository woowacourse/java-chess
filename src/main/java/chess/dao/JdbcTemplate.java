package chess.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class JdbcTemplate {

    private final Connection connection;

    public JdbcTemplate() {
        this.connection = ConnectionProvider.getConnection();
        createTable();
    }

    private void createTable() {
        try {
            String contents = Files.readString(Paths.get("src/main/resources/chess-table.sql"));
            executeDDL(Arrays.asList(contents.split(";")));
        } catch (IOException e) {
            System.err.println("SQL 파일 읽기 오류: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.err.println("DDL 오류: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void executeDDL(final List<String> ddlContents) throws SQLException {
        for (String ddl : ddlContents) {
            connection.prepareStatement(ddl).executeUpdate();
        }
    }

    public long saveAndReturnGeneratedKey(final String sql, final Object... params) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
            for (int index = 0; index < params.length; index++) {
                preparedStatement.setObject(index + 1, params[index]);
            }
            preparedStatement.executeUpdate();

            return getGeneratedKey(preparedStatement);
        } catch (SQLException e) {
            System.err.println("INSERT 오류: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static long getGeneratedKey(PreparedStatement preparedStatement) throws SQLException {
        ResultSet keys = preparedStatement.getGeneratedKeys();
        long generatedGameId = 1L;
        if (keys.next()) {
            generatedGameId = keys.getLong(1);
        }
        return generatedGameId;
    }

    public void save(final String sql, final Object... params) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int index = 0; index < params.length; index++) {
                preparedStatement.setObject(index + 1, params[index]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("INSERT 오류: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void updateOne(final String sql, final Object... params) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int index = 0; index < params.length; index++) {
                preparedStatement.setObject(index + 1, params[index]);
            }
            int effectedColumnCount = preparedStatement.executeUpdate();
            validateOnlyOneExecute(effectedColumnCount);
        } catch (SQLException e) {
            System.err.println("UPDATE 오류: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static void validateOnlyOneExecute(int effectedColumnCount) {
        if (effectedColumnCount != 1) {
            System.err.println("UPDATE 오류: " + "잘못된 쿼리 입니다.");
            throw new RuntimeException("잘못된 업데이트 입니다.");
        }
    }

    public void deleteOne(final String sql, final Object... params) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int index = 0; index < params.length; index++) {
                preparedStatement.setObject(index + 1, params[index]);
            }
            int effectedColumnCount = preparedStatement.executeUpdate();
            validateLessThanOneExecute(effectedColumnCount);
        } catch (SQLException e) {
            System.err.println("DELETE 오류: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static void validateLessThanOneExecute(int effectedColumnCount) {
        if (effectedColumnCount > 1) {
            System.err.println("DELETE 오류: " + "잘못된 쿼리 입니다.");
            throw new RuntimeException("잘못된 업데이트 입니다.");
        }
    }

    public <T> T query(final String sql, final Object condition, final RowMapper<T> rowMapper) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, condition);
            return rowMapper.map(preparedStatement.executeQuery());
        } catch (SQLException e) {
            System.err.println("INSERT 오류: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public <T> T queryWithNoCondition(final String sql, final RowMapper<T> rowMapper) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return rowMapper.map(preparedStatement.executeQuery());
        } catch (SQLException e) {
            System.err.println("INSERT 오류: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
