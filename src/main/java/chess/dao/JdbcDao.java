package chess.dao;

import chess.dto.BoardDto;
import chess.dto.GameRoomDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcDao {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public void save(List<BoardDto> boardDtoList, GameRoomDto gameRoomDto) {
        Connection connection = getConnection();
        int roomId = saveGameRoom(connection, gameRoomDto);
        saveBoard(connection, boardDtoList, roomId);
        closeConnection(connection);
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            return null;
        }
    }

    private int saveGameRoom(Connection connection, GameRoomDto gameRoom) {
        String query = "INSERT INTO room VALUES(?, ?, ?)";

        try (PreparedStatement prepareStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            prepareStatement.setString(1, null); // auto_increment
            prepareStatement.setString(2, gameRoom.getRoomName());
            prepareStatement.setBoolean(3, gameRoom.isWhiteTurn());

            prepareStatement.executeUpdate();

            return getSavedId(prepareStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private void saveBoard(Connection connection, List<BoardDto> boardDtoList, int roomId) {
        String query = "INSERT INTO board VALUES(?, ?, ?, ?)";
        try (PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            for (BoardDto boardDto : boardDtoList) {
                prepareStatement.setString(1, null); // auto_increment
                prepareStatement.setString(2, boardDto.getSource());
                prepareStatement.setString(3, boardDto.getPiece());
                prepareStatement.setInt(4, roomId);
                prepareStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private int getSavedId(PreparedStatement prepareStatement) throws SQLException {
        ResultSet generatedKeys = prepareStatement.getGeneratedKeys();

        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }

        throw new SQLException();
    }

    private int getRoomId(Connection connection, String roomName) {
        String query = "SELECT room_id FROM room WHERE room_name = ?";

        try (PreparedStatement prepareStatement = connection.prepareStatement(query)) {

            prepareStatement.setString(1, roomName);
            ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("room_id");
            }

            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void update(List<BoardDto> boardDtoList, GameRoomDto gameRoom) {
        Connection connection = getConnection();
        int roomId = getRoomId(connection, gameRoom.getRoomName());

        updateGameRoom(connection, gameRoom);
        updateBoard(connection, boardDtoList, roomId);
        closeConnection(connection);
    }

    private void updateGameRoom(Connection connection, GameRoomDto gameRoom) {
        String query = "UPDATE room SET is_white_turn = ? WHERE room_name = ?";

        try (PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            prepareStatement.setBoolean(1, gameRoom.isWhiteTurn());
            prepareStatement.setString(2, gameRoom.getRoomName());

            prepareStatement.executeUpdate();
        } catch (
                SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private void updateBoard(Connection connection, List<BoardDto> boardDtoList, int roomId) {
        String query = "UPDATE board SET piece = ? WHERE source = ? AND room_id = ?";

        try (PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            for (BoardDto boardDto : boardDtoList) {
                prepareStatement.setString(1, boardDto.getPiece());
                prepareStatement.setString(2, boardDto.getSource());
                prepareStatement.setInt(3, roomId);
                prepareStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Optional<GameRoomDto> findGameRoomByName(String roomName) {
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

    public List<BoardDto> findBoardByRoomName(String roomName) {
        String query = "SELECT source, piece FROM board INNER JOIN room ON board.room_id = room.room_id "
                + "WHERE room_name = ? ";
        List<BoardDto> result = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            prepareStatement.setString(1, roomName);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new BoardDto(
                        resultSet.getString("source"),
                        resultSet.getString("piece")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return result;
    }

    public void deleteAllByName(String roomName) {
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
