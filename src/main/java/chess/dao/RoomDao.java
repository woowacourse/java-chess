package chess.dao;

import chess.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RoomDao {
    final Connection connection = DBConnection.getConnection();
    public Map<String, String> findById(String roomId) {
        final String sql = "select * from room  where id = ?";
        try {
            Map<String, String> model = new HashMap<>();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, roomId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            model.put("id", resultSet.getString("id"));
            model.put("status", resultSet.getString("status"));
            return model;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete() {
        final String sql = "delete from room where id = 1";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(String roomId, String team) {
        final String sql = "insert into room (id, status) values(?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, roomId);
            statement.setString(2, team);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatus(String team, String roomId) {
        final String sql1 = "update room set status = ? where id = ?";
        try {
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1, team);
            statement1.setString(2, roomId);
            statement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
