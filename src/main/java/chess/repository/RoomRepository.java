package chess.repository;

import chess.domain.room.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomRepository {
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";

    public List<Room> findAllRooms() throws SQLException {
        String query = "SELECT * FROM ROOM";
        Connection connection = ConnectionManager.makeConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Room> rooms = generateRoomsFrom(resultSet);
        resultSet.close();
        return rooms;
    }

    private List<Room> generateRoomsFrom(ResultSet resultSet) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        while (resultSet.next()) {
            int id = Integer.parseInt(resultSet.getString(ID_COLUMN));
            String name = resultSet.getString(NAME_COLUMN);
            Room room = new Room(id, name);
            rooms.add(room);
        }
        return rooms;
    }

    public void insertRoom(String roomName) throws SQLException {
        String query = "INSERT INTO ROOM (NAME) VALUES (?)";
        Connection connection = ConnectionManager.makeConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, roomName);
        preparedStatement.executeUpdate();
        connection.close();
    }

    public Optional<Room> findLatestRoom() throws SQLException {
        String query = "SELECT * FROM ROOM ORDER BY ID DESC LIMIT 1";
        Connection connection = ConnectionManager.makeConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            return Optional.empty();
        }
        int id = Integer.parseInt(resultSet.getString(ID_COLUMN));
        String name = resultSet.getString(NAME_COLUMN);
        Room room = new Room(id, name);
        connection.close();
        return Optional.of(room);
    }
}
