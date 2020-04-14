package chess.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class SelectJdbcTemplate {

    public Object executeQuery(String query) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(query);
            setParameters(pstmt);
            rs = pstmt.executeQuery();
            return mapRow(rs);
        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }
    }

    public abstract void setParameters(PreparedStatement pstmt) throws SQLException;

    public abstract Object mapRow(ResultSet rs) throws SQLException;
}
