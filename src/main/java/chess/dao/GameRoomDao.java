package chess.dao;

import chess.dto.GameRoomDto;

import java.sql.*;
import java.util.Optional;

public class GameRoomDao {
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

    public void save(GameRoomDto gameRoom) {
        String query = "INSERT INTO room VALUES(?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {

            prepareStatement.setString(1, gameRoom.getRoomName());
            prepareStatement.setBoolean(2, gameRoom.isWhiteTurn());

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Optional<GameRoomDto> findByRoomName(String roomName) {
        String query = "SELECT * FROM room WHERE room_name = ?";

        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            prepareStatement.setString(1, roomName);
            ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(new GameRoomDto(
                        resultSet.getString("room_name"),
                        resultSet.getBoolean("is_white_turn"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return Optional.empty();
    }

    public void update(GameRoomDto gameRoom) {
        String query = "UPDATE room SET is_white_turn = ? WHERE room_name = ?";

        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            prepareStatement.setBoolean(1, gameRoom.isWhiteTurn());
            prepareStatement.setString(2, gameRoom.getRoomName());

            prepareStatement.executeUpdate();
        } catch (
                SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public void deleteByRoomName(String roomName) {
        String query = "DELETE FROM room WHERE room_name = ?";

        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            prepareStatement.setString(1, roomName);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
