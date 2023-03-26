package chess.dao;

import chess.dto.BoardDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {
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

    public void save(List<BoardDto> boardDtoList) {
        String query = "INSERT INTO board VALUES(?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            for (BoardDto boardDto : boardDtoList) {
                prepareStatement.setString(1, null); // auto_increment
                prepareStatement.setString(2, boardDto.getSource());
                prepareStatement.setString(3, boardDto.getPiece());
                prepareStatement.setString(4, boardDto.getRoomName());
                prepareStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<BoardDto> findAllByRoomName(String roomName) {
        String query = "SELECT * FROM board WHERE room_name = ?";
        List<BoardDto> result = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            prepareStatement.setString(1, roomName);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new BoardDto(
                        resultSet.getString("source"),
                        resultSet.getString("piece"),
                        resultSet.getString("room_name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return result;
    }

    public void update(List<BoardDto> boardDtoList) {
        String query = "UPDATE board SET piece = ? WHERE source = ? AND room_name = ?";

        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            for (BoardDto boardDto : boardDtoList) {
                prepareStatement.setString(1, boardDto.getPiece());
                prepareStatement.setString(2, boardDto.getSource());
                prepareStatement.setString(3, boardDto.getRoomName());
                prepareStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void deleteAllByRoomName(String roomName) {
        String query = "DELETE FROM board WHERE room_name = ?";

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
