package chess.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class JdbcTemplate {

    public void executeUpdate(String query) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(query);
            setParameters(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, pstmt);
        }
    }

    public abstract void setParameters(PreparedStatement pstmt) throws SQLException;
}
