package chess.domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDao {
    private DBConnector dbConnector = new DBConnector();

    public boolean isExistedPlayer(String playerName) {
        String query = "SELECT * FROM player WHERE (player_name = ?)";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, playerName);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) return false;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
