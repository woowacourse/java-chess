package web.dao;

import chess.dto.GameDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static web.dao.DBConnector.getConnection;

public class GameDao {

    public void save(GameDto gameDto) {
        final Connection connection = getConnection();
        final String sql = "insert into game (room_name, turn_color) value (?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameDto.getRoomName());
            statement.setString(2, gameDto.getTurnColor());
            statement.execute();
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                throw new IllegalArgumentException("[ERROR] 중복된 이름의 게임이 존재합니다.");
            }
            e.printStackTrace();
        }
    }

    public GameDto findByRoomName(String roomName) {
        final Connection connection = getConnection();
        final String sql = "select room_name, turn_color " +
                "from game where room_name=?";
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, roomName);
            final ResultSet result = statement.executeQuery();
            if (!result.next()) {
                return null;
            }
            return new GameDto(result.getString("room_name"), result.getString("turn_color"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(GameDto gameDto) {
        final Connection connection = getConnection();
        final String sql = "update game " +
                "set turn_color=? " +
                "where room_name=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameDto.getTurnColor());
            statement.setString(2, gameDto.getRoomName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String roomName) {
        final Connection connection = getConnection();
        final String sql = "delete from game where room_name=?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, roomName);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
