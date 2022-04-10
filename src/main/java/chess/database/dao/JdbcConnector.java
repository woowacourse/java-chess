package chess.database.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JdbcConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private static final int START_INDEX = 1;

    private final PreparedStatement statement;
    private int parameterIndex;

    public JdbcConnector(PreparedStatement statement) {
        this.statement = statement;
        this.parameterIndex = START_INDEX;
    }

    public JdbcConnector(PreparedStatement statement, int parameterIndex) {
        this.statement = statement;
        this.parameterIndex = parameterIndex;
    }

    private static Connection getConnection() {
        loadDriver();
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ignored) {
        }
        throw new IllegalStateException("[ERROR] 데이터베이스 연결에 실패했습니다.");
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ignored) {
            throw new IllegalStateException("[ERROR] JDBC 연결에 실패했습니다.");
        }
    }

    public static JdbcConnector query(String sql) {
        final Connection connection = getConnection();
        try {
            return new JdbcConnector(connection.prepareStatement(sql));
        } catch (SQLException ignored) {
        }
        throw new IllegalArgumentException("[ERROR] SQL 구문이 올바르지 않습니다.");
    }

    public JdbcConnector parameters(int... arguments) {
        int copyIndex = parameterIndex;

        for (int argument : arguments) {
            copyIndex = setInt(copyIndex, argument);
        }
        return new JdbcConnector(statement, copyIndex);
    }

    public JdbcConnector parameters(String... arguments) {
        int copyIndex = parameterIndex;

        for (String argument : arguments) {
            copyIndex = setString(copyIndex, argument);
        }
        return new JdbcConnector(statement, copyIndex);
    }

    private int setInt(int copyIndex, int argument) {
        try {
            statement.setInt(copyIndex++, argument);
            return copyIndex;
        } catch (SQLException ignored) {
        }
        throw new IllegalArgumentException(String.format("[ERROR] 값 세팅에 실패했습니다. : %d", argument));
    }

    private int setString(int copyIndex, String argument) {
        try {
            statement.setString(copyIndex++, argument);
            return copyIndex;
        } catch (SQLException ignored) {
        }
        throw new IllegalArgumentException(String.format("[ERROR] 값 세팅에 실패했습니다. : %s", argument));
    }

    public JdbcConnector batch() {
        JdbcConnector copy = new JdbcConnector(this.statement, 1);
        try {
            copy.statement.addBatch();
            copy.statement.clearParameters();
            return copy;
        } catch (SQLException ignored) {
        }
        throw new IllegalArgumentException("[ERROR] 배치 쿼리 추가에 실패했습니다.");
    }

    public ResultSetHolder executeQuery() {
        try {
            return new ResultSetHolder(statement.executeQuery());
        } catch (SQLException ignored) {
        }
        throw new IllegalStateException("[ERROR] 쿼리 실행에 실패했습니다.");
    }

    public void executeUpdate() {
        try {
            statement.executeUpdate();
        } catch (SQLException ignored) {
            throw new IllegalStateException("[ERROR] 업데이트 쿼리를 실패했습니다.");
        }
    }

    public void executeBatch() {
        try {
            statement.executeBatch();
        } catch (SQLException ignored) {
            throw new IllegalStateException("[ERROR] 배치 쿼리를 실패했습니다.");
        }
    }

    static class ResultSetHolder {

        private final ResultSet resultSet;

        public ResultSetHolder(ResultSet resultSet) {
            this.resultSet = resultSet;
        }

        public boolean next() {
            try {
                return resultSet.next();
            } catch (SQLException ignored) {
            }
            throw new IllegalStateException("[ERROR] ResultSet의 next 실행에 실패했습니다.");
        }

        public List<String> getStrings(String... columns) {
            return Arrays.stream(columns)
                .map(this::getString)
                .collect(Collectors.toList());
        }

        public String getString(String column) {
            try {
                return resultSet.getString(column);
            } catch (SQLException ignored) {
            }
            throw new IllegalArgumentException(String.format("[ERROR] 해당하는 칼럼이 없습니다. : %s", column));
        }

        public int getInteger(String column) {
            try {
                return resultSet.getInt(column);
            } catch (SQLException ignored) {
            }
            throw new IllegalArgumentException(String.format("[ERROR] 해당하는 칼럼이 없습니다. : %s", column));
        }
    }
}
