package chess.dao;

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
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static Connection loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JdbcConnector query(String sql) {
        final Connection connection = getConnection();
        try {
            return new JdbcConnector(connection.prepareStatement(sql));
        } catch (SQLException ignored) {
            toIllegalArgumentException(sql);
        }
        throw new IllegalArgumentException("[ERROR] SQL 구문이 올바르지 않습니다.");
    }

    private static void toIllegalArgumentException(String sql) throws IllegalStateException {
        throw new IllegalArgumentException(String.format("[ERROR] SQL 예외가 발생했습니다 : %s", sql));
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return copyIndex;
    }

    private int setString(int copyIndex, String argument) {
        try {
            statement.setString(copyIndex++, argument);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return copyIndex;
    }

    public JdbcConnector batch() {
        JdbcConnector copy = new JdbcConnector(this.statement, 1);
        try {
            copy.statement.addBatch();
            copy.statement.clearParameters();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return copy;
    }

    public ResultSetHolder executeQuery() {
        try {
            return new ResultSetHolder(statement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("[ERROR] excute failed");
    }

    public void executeUpdate() {
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeBatch() {
        try {
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        public List<String> getStrings(String... columns) {
            return Arrays.stream(columns)
                .map(this::getString)
                .collect(Collectors.toList());
        }

        public String getString(String column) {
            try {
                return resultSet.getString(column);
            } catch (SQLException e) {
                toIllegalArgumentException(column);
            }
            throw new IllegalArgumentException("[ERROR] getString failed");
        }

        public List<Integer> getIntegers(String... columns) {
            return Arrays.stream(columns)
                .map(this::getInteger)
                .collect(Collectors.toList());
        }

        public int getInteger(String column) {
            try {
                return resultSet.getInt(column);
            } catch (SQLException exception) {
                toIllegalArgumentException(column);
            }
            throw new IllegalArgumentException("[ERROR] getInteger failed");
        }
    }
}
