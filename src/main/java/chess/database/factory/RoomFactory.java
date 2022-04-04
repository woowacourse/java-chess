package chess.database.factory;

import chess.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomFactory {
    public static boolean existRoom(String roomId) {
        final Connection connection = DBConnection.getConnection();
        final String sql = "select * from room  where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, roomId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }
            return !resultSet.getString("id").isEmpty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
