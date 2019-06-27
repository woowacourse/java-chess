package chess.dao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessJdbcTemplate {

    private static ChessJdbcTemplate chessJdbcTemplate;
    private DataSource dataSource;

    private ChessJdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ChessJdbcTemplate getInstance(DataSource dataSource) {
        if (chessJdbcTemplate == null) {
            chessJdbcTemplate = new ChessJdbcTemplate(dataSource);
        }
        return chessJdbcTemplate;
    }

    public void executeUpdate(String query) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeUpdate(String query, List<Object> queryValues) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = createPreparedStatement(conn, query, queryValues)) {

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement createPreparedStatement(Connection conn, String query, List<Object> queryValues) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(query);
        for (int index = 0; index < queryValues.size(); index++) {
            stmt.setObject(index + 1, queryValues.get(index));
        }
        return stmt;
    }

    public List<Map<String, Object>> executeQuery(String query) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            return getResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Map<String, Object>> executeQuery(String query, List<Object> queryValues) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = createPreparedStatement(connection, query, queryValues);
             ResultSet rs = pstmt.executeQuery()) {

            return getResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Map<String, Object>> getResults(ResultSet rs) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();

        while (rs.next()) {
            Map<String, Object> resultRow = getResult(rs);
            results.add(resultRow);
        }
        return results;
    }

    private Map<String, Object> getResult(ResultSet rs) throws SQLException {
        Map<String, Object> result = new HashMap<>();

        ResultSetMetaData rsMetaData = rs.getMetaData();
        for (int index = 1; index <= rsMetaData.getColumnCount(); index++) {
            String columnName = rsMetaData.getColumnName(index);
            result.put(columnName, rs.getObject(columnName));
        }
        return result;
    }
}
