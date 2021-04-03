package chess.repository;

import chess.utils.DBConnector;

import java.sql.*;

public class ChessRepository extends DBConnector {

    public ResultSet getSavedBoardInfo() throws SQLException {
        String query = "select * from board;";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        return pstmt.executeQuery();
    }

    public void updateBoard() throws SQLException {
        String query = "update board set piece = ? where position = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, "thisispiece");
        pstmt.setString(2, "a1");
        pstmt.executeUpdate();
    }
}
