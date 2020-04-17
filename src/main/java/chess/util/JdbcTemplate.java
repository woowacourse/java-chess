package chess.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {

    public void executeUpdate(String query, ParameterSetter ps) throws SQLException {
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            ps.setParameters(pstmt);
            pstmt.executeUpdate();
        }
    }

    public void executeUpdate(String query, Object... parameters) throws SQLException {
        executeUpdate(query, createParameterSetter(parameters));
    }

    public <T> T executeQuery(String query, RowMapper<T> mapper, ParameterSetter ps) throws SQLException {
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            ps.setParameters(pstmt);
            try (ResultSet rs = pstmt.executeQuery()) {
                return mapper.mapRow(rs);
            }
        }
    }

    public <T> T executeQuery(String query, RowMapper<T> mapper, Object... parameters) throws SQLException {
        return executeQuery(query, mapper, createParameterSetter(parameters));
    }

    private ParameterSetter createParameterSetter(Object[] parameters) {
        return pstmt -> {
            for (int i = 0; i < parameters.length; i++) {
                pstmt.setObject(i + 1, parameters[i]);
            }
        };
    }
}
