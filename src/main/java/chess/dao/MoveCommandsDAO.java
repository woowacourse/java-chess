package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static chess.dao.DAO.closeConnection;
import static chess.dao.DAO.getConnection;

public class MoveCommandsDAO {

    public void addMoveCommands(int game_id, String moveCommands) throws SQLException {
        String query = "INSERT INTO move_commands VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, 1);
        pstmt.setInt(2, game_id);
        pstmt.setString(2, moveCommands);
        pstmt.executeUpdate();
        closeConnection(getConnection());
    }

    public String findByMoveCommandsId(int game_id) throws SQLException {
        String query = "SELECT * FROM move_commands WHERE game_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, game_id);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        return rs.getString("command");
    }
}
