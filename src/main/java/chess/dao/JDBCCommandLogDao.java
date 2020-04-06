package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JDBCCommandLogDao {

    public void add(String command) throws SQLException {
        Connection connection = getConnection();
        String query = "insert into commandlog (command) values (?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, command);

        preparedStatement.executeUpdate();
        closeConnection(connection);
    }

    public List<String> getAllByOldOneFirst() throws SQLException {
        Connection connection = getConnection();

        String query = "select command from commandlog order by execute_order asc";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<String> commands = new ArrayList<>();

        while (resultSet.next()) {
            commands.add(resultSet.getString("command"));
        }
        closeConnection(connection);
        return Collections.unmodifiableList(commands);
    }

    private Connection getConnection() {
        Connection connection = null;
        String serverAddress = "localhost:3306";
        String databaseName = "chess";
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "chess";
        String password = "chess";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("!! JDBC Driver load 오류: " + e.getMessage());
        }

        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://" + serverAddress + "/" + databaseName + option,
                userName, password);
            System.out.println("DB에 정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.out.println("DB 연결 오류: " + e.getMessage());
            e.printStackTrace();
        }

        return connection;
    }

    private void closeConnection(Connection connection) {
        if (connection == null) {
            throw new IllegalArgumentException("null connection 을 닫을 수 없습니다.");
        }
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }
}
