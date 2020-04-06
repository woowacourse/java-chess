package chess.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CurrentTeamDAO {
    public void addCurrentTeam(int chessBoardId, String currentTeam) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        String query = "INSERT INTO currentTeam (team, chessBoardId) VALUES(?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, currentTeam);
        pstmt.setInt(2, chessBoardId);
        pstmt.executeUpdate();
        ConnectionManager.closeConnection(con);
    }

    public void updateCurrentTeam(int chessBoardId, String currentTeam) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        String query = "UPDATE currentTeam SET team = ? WHERE chessBoardId = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, currentTeam);
        pstmt.setInt(2, chessBoardId);
        pstmt.executeUpdate();
        ConnectionManager.closeConnection(con);
    }

    public void deleteCurrentTeam(int chessBoardId) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        String query = "DELETE FROM currentTeam WHERE chessBoardId = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, chessBoardId);
        pstmt.executeUpdate();
        ConnectionManager.closeConnection(con);
    }
}
