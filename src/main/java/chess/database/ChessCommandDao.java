package chess.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChessCommandDao {
    public Connection getConnection() {
        Connection connection = null;
        String server = "localhost:13306";
        String database = "WOOWA";
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //어떤 드라이버에 연결할지
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password); //진짜 연결
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("connection 오류: " + e.getMessage());
        }
    }

    public void addCommand(String cmd) throws SQLException {
        String query = "INSERT INTO commands VALUES (?)";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setString(1, cmd);
        preparedStatement.executeUpdate();
    }

    public void deleteCommands() throws SQLException {
        String query = "TRUNCATE commands";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.execute();
    }

    public List<String> selectCommands() throws SQLException {
        String query = "SELECT * FROM commands";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();

        if (!rs.next()) {
            return null;
        }

        List<String> commands = new ArrayList<>();
        commands.add(rs.getString("command"));

        while (rs.next()) {
            commands.add(rs.getString("command"));
        }
        return commands;
    }
}
