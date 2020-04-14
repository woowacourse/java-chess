package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

    private String option = "?useSSL=false&serverTimezone=UTC";
    private String server;
    private String database;
    private String userName;
    private String password;

    public JDBCTemplate(String server, String database, String userName, String password) {
        this.server = server;
        this.database = database;
        this.userName = userName;
        this.password = password;
    }

    private Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
        } catch (ClassNotFoundException ce) {
            System.err.println(" !! JDBC Driver load 오류: " + ce.getMessage());
        } catch (SQLException se) {
            System.err.println("연결 오류:" + se.getMessage());
        }

        return con;
    }

    public <T> T executeQuery(String query, PreparedStatementSetter setter, ResultSetMapper<T> mapper) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setter.setPreparedStatement(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            return mapper.map(resultSet);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    public <T> T executeQuery(String query, ResultSetMapper<T> mapper) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
            return mapper.map(resultSet);
        }

    }

    public <T> T executeUpdate(String query, PreparedStatementSetter setter, ResultSetMapper<T> mapper) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setter.setPreparedStatement(preparedStatement);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            return mapper.map(resultSet);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    public void executeUpdate(String query, PreparedStatementSetter setter) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setter.setPreparedStatement(preparedStatement);
            preparedStatement.executeUpdate();
        }
    }
}
