package chess.dao;

import chess.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IsFinishedDao {

    private final Connection conn;

    public IsFinishedDao() {
        this.conn = DBConnector.getConnection();
    }

    public boolean load() throws SQLException {
        String query = "SELECT * FROM isFinished";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return false;
        }
        return rs.getBoolean("isFinished");
    }

    public void save(boolean isFinished) throws SQLException {
        delete();
        String query = "INSERT INTO isFinished VALUES (?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setBoolean(1, isFinished);
        pstmt.executeUpdate();
    }

    public void delete() throws SQLException {
        String query = "DELETE FROM isFinished";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.executeUpdate();
    }

}
