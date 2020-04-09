package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrentTeamDAO {
    public void addCurrentTeam(ChessBoard chessBoard, CurrentTeam currentTeam) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        String query = "INSERT INTO currentTeam (team, chessBoardId) VALUES(?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, currentTeam.getCurrentTeam());
        pstmt.setInt(2, chessBoard.getChessBoardId());
        pstmt.executeUpdate();
        ConnectionManager.closeConnection(con);
    }

    public void updateCurrentTeam(ChessBoard chessBoard, CurrentTeam currentTeam) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        String query = "UPDATE currentTeam SET team = ? WHERE chessBoardId = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, currentTeam.getCurrentTeam());
        pstmt.setInt(2, chessBoard.getChessBoardId());
        pstmt.executeUpdate();
        ConnectionManager.closeConnection(con);
    }

    public void deleteCurrentTeam(ChessBoard chessBoard) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        String query = "DELETE FROM currentTeam WHERE chessBoardId = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, chessBoard.getChessBoardId());
        pstmt.executeUpdate();
        ConnectionManager.closeConnection(con);
    }

    public CurrentTeam findCurrentTeam(ChessBoard chessBoard) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        String query = "SELECT team FROM currentTeam WHERE chessBoardId = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, chessBoard.getChessBoardId());
        ResultSet rs = pstmt.executeQuery();

        while (!rs.next()) {
            return null;
        }
        CurrentTeam currentTeam = new CurrentTeam(
                rs.getString("team")
        );
        ConnectionManager.closeConnection(con);
        return currentTeam;
    }
}
