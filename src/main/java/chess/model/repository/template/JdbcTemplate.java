package chess.model.repository.template;

import static chess.model.repository.connector.ChessMySqlConnector.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTemplate {

    public void executeUpdate(String query, PreparedStatementSetter pss) throws SQLException {
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pss.setParameter(pstmt);
            pstmt.executeUpdate();
        }
    }

    public void executeUpdateWhenLoop(String query, PreparedStatementSetter loopPss)
        throws SQLException {
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            loopPss.setParameter(pstmt);
        }
    }

    public int executeUpdateWithGeneratedKey(String query, PreparedStatementSetter pss)
        throws SQLException {
        try (Connection conn = getConnection();
            PreparedStatement pstmt =
                conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pss.setParameter(pstmt);
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                throw new SQLException();
            }
        }
    }
}
