package chess.dao;

import chess.dto.RoomDTO;
import chess.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class RoomDAO {

    public void createRoom(final String name) {
        String query = "INSERT INTO room (title, black_user, white_user, status) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, name);
            statement.setInt(2, 1); // default black 유저 id
            statement.setInt(3, 2); // default white 유저 id
            statement.setInt(4, 1); // 상태(진행중: 1 / 종료됨: 0)
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("새로운 방 생성이 실패했습니다.");
        }
    }

    public List<RoomDTO> allRooms() {
        String query = "SELECT room.id, room.title, black.nickname AS black_user, white.nickname AS white_user, room.status " +
                "FROM room JOIN user as black on black.id = room.black_user " +
                "JOIN user as white on white.id = room.white_user ORDER BY room.status DESC, room.id DESC";

        List<RoomDTO> rooms = new ArrayList<>();

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();) {
            getRoomInformation(resultSet, rooms);
        } catch (SQLException e) {
            throw new DataAccessException("방 목록 불러오기를 실패했습니다.");
        }

        return rooms;
    }

    private void getRoomInformation(final ResultSet resultSet, final List<RoomDTO> rooms) throws SQLException {
        while (resultSet.next()) {
            boolean playing = false;
            int status = resultSet.getInt("status");
            if (status == 1) {
                playing = true;
            }
            RoomDTO roomDTO = new RoomDTO(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("black_user"),
                    resultSet.getString("white_user"),
                    status,
                    playing
            );
            rooms.add(roomDTO);
        }
    }

    public void changeStatusEndByRoomId(final String roomId) {
        String query = "UPDATE room SET status = 0 WHERE id = ?";

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, roomId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("방의 게임 상태 변경을 실패했습니다.");
        }
    }

    public List<String> allRoomIds() {
        String query = "SELECT id FROM room";

        List<String> ids = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                ids.add(Integer.toString(id));
            }
        } catch (SQLException e) {
            throw new DataAccessException("방 목록 불러오기를 실패했습니다.");
        }

        return ids;
    }

}
