package chess.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChessCommandDao {
    public Connection getConnection() {
        Connection connection = null;
        final String server = "localhost:13306";
        final String database = "WOOWA";
        final String option = "?useSSL=false&serverTimezone=UTC";
        final String userName = "root";
        final String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //어떤 드라이버에 연결할지
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver load 오류:" + e.getMessage());
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    public void addCommand(ChessCommand command) throws SQLException {
        String query = "INSERT INTO commands VALUES (?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, command.getCommand());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

    }

    public void deleteCommands() throws SQLException {
        String query = "TRUNCATE commands";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<String> selectCommands() throws SQLException {
        String query = "SELECT * FROM commands";
        List<String> commands = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                commands.add(rs.getString("command"));
            }
            return commands;
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return commands;
    }
}
