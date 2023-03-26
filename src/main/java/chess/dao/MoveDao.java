package chess.dao;

import chess.dto.MoveDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoveDao {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            return null;
        }
    }

    public void addMove(MoveDto move) {
        String query = "INSERT INTO move VALUES(?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            prepareStatement.setString(1, null); // auto_increment
            prepareStatement.setString(2, move.getSource());
            prepareStatement.setString(3, move.getTarget());

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<MoveDto> findAll() {
        String query = "SELECT * FROM move";
        List<MoveDto> result = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new MoveDto(
                        resultSet.getString("source"),
                        resultSet.getString("target")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return result;
    }

    public void deleteAll() {
        String query = "DELETE FROM move";

        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
