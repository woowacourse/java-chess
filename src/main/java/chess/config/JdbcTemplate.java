package chess.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class JdbcTemplate {
    private final DbConnector dbConnector;

    public JdbcTemplate(final DataSource dataSource) {
        this.dbConnector = new DbConnector(dataSource);
    }

    public int executeUpdate(String sql) {
        return executeUpdate(sql, Collections.emptyList());
    }

    public int executeUpdate(final String sql, final List<Object> params) {
        int result = 0;
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ps = createPrepareStatement(conn, sql, params)) {
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public <T> T executeQuery(final String sql, final List<Object> params, RowMapper<T> rowMapper) {
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ps = createPrepareStatement(conn, sql, params);
             ResultSet rs = ps.executeQuery()) {
            return rowMapper.mapRow(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T executeQuery(final String sql, RowMapper<T> rowMapper) {
        return executeQuery(sql, Collections.emptyList(), rowMapper);
    }

    private PreparedStatement createPrepareStatement(final Connection conn, final String sql, final List<Object> params) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(sql);
        int index = 1;
        for (final Object param : params) {
            ps.setObject(index++, param);
        }
        return ps;
    }
}
