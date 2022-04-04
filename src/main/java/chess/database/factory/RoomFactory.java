package chess.database.factory;

import chess.dao.RoomDao;
import chess.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomFactory {
    public static RoomDao findById(String roomId) {
        final Connection connection = DBConnection.getConnection();
        final String sql = "select * from room  where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, roomId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new RoomDao(resultSet.getString("id"), resultSet.getString("status"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void delete() {
        final Connection connection = DBConnection.getConnection();
        final String sql = "delete from room where id = 1";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void save(String roomId, String team) {
        final Connection connection = DBConnection.getConnection();
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

    public static void updateStatus(String team, String roomId) {
        final Connection connection = DBConnection.getConnection();
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
