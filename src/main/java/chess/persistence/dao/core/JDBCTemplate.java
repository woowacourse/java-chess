package chess.persistence.dao.core;

import chess.persistence.dao.connector.DataSourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class JDBCTemplate {
    private JDBCTemplate() {
    }

    public static JDBCTemplate getInstance() {
        return JdbcTemplateHandler.INSTANCE;
    }

    public long excuteUpdate(String query, List<Object> params) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement pstmt = createPstmt(connection, query, params)) {
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQLException 발생 -> 데이터 넣는 부분\n" + e.getMessage());
        }
    }

    public <T> T queryForObject(String query, RowMapper<T> rm) {
        return queryForObject(query, Collections.emptyList(), rm);
    }

    public <T> T queryForObject(String query, List<Object> params, RowMapper<T> rm) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection();
             PreparedStatement pstmt = createPstmt(connection, query, params);
             ResultSet rs = pstmt.executeQuery()) {
            return rm.mapRow(rs);
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQLException 발생 -> 데이터 찾는 부분\n" + e.getMessage());
        }
    }

    private PreparedStatement createPstmt(final Connection connection, String query, List<Object> params) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(query);
        int index = 1;
        for (Object param : params) {
            ps.setObject(index++, param);
        }
        return ps;
    }

    private static class JdbcTemplateHandler {
        private static final JDBCTemplate INSTANCE = new JDBCTemplate();
    }
}
