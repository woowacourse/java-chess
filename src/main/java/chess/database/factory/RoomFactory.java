package chess.database.factory;

import chess.dao.BoardDao;
import chess.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomFactory {
    public static String findByPosition(String roomId) {
        final Connection connection = DBConnection.getConnection();
        final String sql = "select * from room  where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, roomId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return resultSet.getString("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
