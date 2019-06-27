package chess.dao;

import chess.db.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCTemplate {
    private static JDBCTemplate jdbcTemplate = new JDBCTemplate();

    private JDBCTemplate() {
    }

    public static JDBCTemplate getInstance() {
        return jdbcTemplate;
    }

    public void updateQuery(String query, Object... queryValues) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement pstmt = getPreparedStatement(query, connection, queryValues)) {
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public List<Map<String, String>> selectQuery(String query, Object... queryValues) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement pstmt = getPreparedStatement(query, connection, queryValues);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return new ArrayList<>();
            }

            List<Map<String, String>> result = new ArrayList<>();
            do {
                Map<String, String> map = new HashMap<>();
                ResultSetMetaData md = rs.getMetaData();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    String columnName = md.getColumnName(i);
                    map.put(columnName, rs.getString(columnName));
                }
                result.add(map);
            } while (rs.next());
            rs.close();

            return result;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private PreparedStatement getPreparedStatement(String query, Connection connection, Object... queryValues) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(query);
        if (queryValues == null) {
            return pstmt;
        }
        setParameter(pstmt, queryValues);
        return pstmt;
    }

    private void setParameter(PreparedStatement pstmt, Object... queryValues) throws SQLException {
        for (int i = 0; i < queryValues.length; i++) {
            pstmt.setObject(i + 1, queryValues[i]);
        }
    }

}
