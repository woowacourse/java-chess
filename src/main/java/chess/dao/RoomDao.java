package chess.dao;

import chess.database.DBConnection;
import chess.domain.Team;
import chess.dto.RoomDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RoomDao {
    final Connection connection = DBConnection.getConnection();

    public RoomDto findById(int roomId) {
        final String sql = "select * from room  where id = ?";
        try {
            Map<String, String> model = new HashMap<>();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, roomId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            Long id = resultSet.getLong("id");
            Team status = Team.find(resultSet.getString("status"));
            return new RoomDto(id, status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(int roomId) {
        final String sql = "delete from room where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, roomId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(int roomId, String team) {
        final String sql = "insert into room (id, status) values(?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, roomId);
            statement.setString(2, team);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatus(String team, int roomId) {
        final String sql = "update room set status = ? where id = ?";
        try {
            PreparedStatement statement1 = connection.prepareStatement(sql);
            statement1.setString(1, team);
            statement1.setInt(2, roomId);
            statement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
