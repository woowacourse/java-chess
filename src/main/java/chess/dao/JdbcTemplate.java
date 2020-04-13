package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.util.JDBCConnector;

public class JdbcTemplate {

    public void executeUpdate(String query, PreparedStatementSetter pss) throws SQLException {
        try (Connection con = JDBCConnector.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)
        ) {
            pss.setParameters(pstmt);
            pstmt.executeUpdate();
        }
    }

    public void executeBatch(String query, PreparedStatementSetter pss) throws SQLException {
        try (Connection con = JDBCConnector.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)
        ) {
            pss.setParameters(pstmt);
            pstmt.executeBatch();
        }
    }

    public Object executeQuery(String query, PreparedStatementSetter pss, RowMapper rm) throws SQLException {
        try (Connection con = JDBCConnector.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)
        ) {
            pss.setParameters(pstmt);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rm.mapRow(rs);
            }
        }
    }
}
