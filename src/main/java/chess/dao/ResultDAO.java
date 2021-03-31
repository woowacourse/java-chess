package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultDAO {
    private final ConnectDB connectDB;

    public ResultDAO(ConnectDB connectDB) {
        this.connectDB = connectDB;
    }

    public void saveGameResult(String roomId, int winnerId, int loserId) throws SQLException {
        String query = "INSERT INTO result (game_id, winner, loser) VALUES (?, ?, ?)";
        PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
        statement.setString(1, roomId);
        statement.setInt(2, winnerId);
        statement.setInt(3, loserId);
        statement.executeUpdate();
    }

    public int winCountByUserId(int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM result WHERE winner = ?";
        PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            return 0;
        }

        return resultSet.getInt(1);
    }

    public int loseCountByUserId(int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM result WHERE loser = ?";
        PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            return 0;
        }

        return resultSet.getInt(1);
    }
}
