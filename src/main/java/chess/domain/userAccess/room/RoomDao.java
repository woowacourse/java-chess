package chess.domain.userAccess.room;

import chess.domain.userAccess.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static chess.domain.userAccess.DbConnection.getConnection;

public class RoomDao {

    public void createRoom(User user) {
        try {
            String query = "INSERT INTO room VALUES(?,?,?)";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, user.userId());
            preparedStatement.setString(3, "");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("[ERROR] 게임 방 추가 예외");
        }
    }

    public Room findRoomByUserIdAndCommands(String userId, String commands) {
        try {
            String query = "SELECT * FROM room WHERE user_id = ? and commands = ?";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, commands);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int roomId = resultSet.getInt("room_id");
            return new Room(roomId, userId, commands);
        } catch (SQLException e) {
            throw new IllegalArgumentException("[ERROR] 유저 아이디와 커맨드로 게임 방을 찾기 예외");
        }
    }

    public List<Room> findRoomsByUser(User user) {
        try {
            String query = "SELECT * FROM room WHERE user_id = ?";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.userId());
            ResultSet resultSet = preparedStatement.executeQuery();
            return makeRoomsByResultSet(resultSet);
        } catch (SQLException e) {
            throw new IllegalArgumentException("[ERROR] 유저로 게임 방들 찾기 예외");
        }
    }

    private List<Room> makeRoomsByResultSet(ResultSet resultSet) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        while (resultSet.next()) {
            int roomId = resultSet.getInt("room_id");
            String userId = resultSet.getString("user_id");
            String commands = resultSet.getString("commands");
            rooms.add(new Room(roomId, userId, commands));
        }
        return rooms;
    }

    public void updateRoomByRoomId(int roomId, String commands) {
        try {
            String query = "UPDATE room SET commands = ? WHERE room_id = ?";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, commands);
            preparedStatement.setInt(2, roomId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("[ERROR] 게임 방의 커맨드 업데이트 예외");
        }
    }

    public void deleteRoomByRoomId(int roomId) {
        try {
            String query = "DELETE FROM room WHERE room_id = ?";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, roomId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("[ERROR] 게임 방 삭제 예외");
        }
    }
}
