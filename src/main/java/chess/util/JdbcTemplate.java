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
            try {
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                System.err.println("con 오류:" + e.getMessage());
            }
        }
    }

    public void executeUpdate(String query, Object... parameters) throws SQLException {
        executeUpdate(query, createParameterSetter(parameters));
    }

    public <T> T executeQuery(String query, RowMapper<T> mapper, ParameterSetter ps) throws SQLException {
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
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                System.err.println("con 오류:" + e.getMessage());
            }
        }
    }

    public <T> T executeQuery(String query, RowMapper<T> mapper, Object... parameters) throws SQLException {
        return executeQuery(query, mapper, createParameterSetter(parameters));
    }

    private ParameterSetter createParameterSetter(Object[] parameters) {
        return new ParameterSetter() {
            @Override
            public void setParameters(PreparedStatement pstmt) throws SQLException {
                for (int i = 0; i < parameters.length; i++) {
                    pstmt.setObject(i + 1, parameters[i]);
                }
            }
        };
    }
}
