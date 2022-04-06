package chess.model.dao;

import java.sql.*;

public class TurnDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (final Exception e) {
            System.out.println("커넥션 연결에 실패하였습니다.");
            e.printStackTrace();
        }
        return conn;
    }

    public void init() {
        String query = "insert into turns (turn) values (?)";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, "WHITE");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String findOne() {
        String query = "select turn from turns limit 1";
        String turn = "";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
             turn = resultSet.getString("turn");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return turn;
    }

    public void update(String nextTurn) {
        String query = "UPDATE turns SET turn = (?)";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, nextTurn);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
