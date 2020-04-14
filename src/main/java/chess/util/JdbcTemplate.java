package chess.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {
    public void executeUpdate(String query, ParameterSetter ps) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(query);
            ps.setParameters(pstmt);
            pstmt.executeUpdate();
        } finally {
            JdbcUtil.close(conn, pstmt);
        }
    }

    public Object executeQuery(String query, ParameterSetter ps, RowMapper mapper) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(query);
            ps.setParameters(pstmt);
            rs = pstmt.executeQuery();
            return mapper.mapRow(rs);
        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }
    }
}
