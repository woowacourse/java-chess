package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import chess.util.JDBCConnector;

public abstract class JdbcTemplate {

    public void executeUpdate(String query) throws SQLException {
        try (Connection con = JDBCConnector.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)
        ) {
            setParameters(pstmt);
            pstmt.executeUpdate();
        }
    }

    public void executeBatch(String query) throws SQLException {
        try (Connection con = JDBCConnector.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)
        ) {
            setParameters(pstmt);
            pstmt.executeBatch();
        }
    }

    public abstract void setParameters(final PreparedStatement pstmt) throws SQLException;
}
