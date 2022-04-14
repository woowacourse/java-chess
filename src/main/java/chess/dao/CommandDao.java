package chess.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        loadDriver();
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("프로그램 실행 중 오류가 발생하였습니다. (SQL connection error)");
        }
        return connection;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final Exception e) {
            e.printStackTrace();
            throw new RuntimeException("드라이버가 로드되지 않았습니다.");
        }
    }

    public void save(final String command) {
        final String sql = "insert into command (command) values (?)";
        try (final Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, command);
            statement.executeUpdate();
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("프로그램 실행 중 오류가 발생하였습니다. (commands save error)");
        }
    }

    public List<String> findAll() {
        final String sql = "select command from command";
        final List<String> commands = new ArrayList<>();
        try (final Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql);
             final ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                commands.add(resultSet.getString("command"));
            }
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("프로그램 실행 중 오류가 발생하였습니다. (commands find error)");
        }
        return commands;
    }

    public void deleteAll() {
        final String sql = "delete from command";
        try (final Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.executeUpdate();
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("게임 시작 중 오류가 발생하였습니다.");
        }
    }
}
