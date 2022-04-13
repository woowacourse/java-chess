package chess.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.game.state.Player;
import chess.web.dto.RoomDto;
import chess.web.utils.DBConnector;

public class RoomDao {
    private final Connection connection = DBConnector.getConnection();

    public int save(RoomDto roomDto) throws SQLException {
        final String sql = "insert into room (name, turn) values (?, ?)";
        final PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, roomDto.getName());
        statement.setString(2, roomDto.getPlayer());
        statement.executeUpdate();
        ResultSet result = statement.getGeneratedKeys();

        if (result.next()) {
            return result.getInt(1);
        }

        return 0;
    }

    public Player findTurnById(int roomId) throws SQLException {
        final String sql = "select turn from room where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, roomId);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return Player.valueOf(resultSet.getString("turn"));
    }

    public List<RoomDto> findAll() throws SQLException{
        final String sql = "select id, name, turn from room";
        List<RoomDto> rooms = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            RoomDto roomDto = RoomDto.of(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("turn")
            );
            rooms.add(roomDto);
        }
        return rooms;
    }

    public void updateById(Player player, int roomId) throws SQLException {
        final String sql = "update room set turn = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, player.name());
        statement.setInt(2, roomId);
        statement.executeUpdate();
    }

    public void remove(int id) throws SQLException {
        final String sql = "delete from room where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
