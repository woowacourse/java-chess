package chess.application;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessJDBCTemplate {
    private static final String ERROR_INVALID_EXECUTE_UPDATE_MESSAGE = "올바르지 않은 executeUpdate 실행입니다.";
    private static final String ERROR_INVALID_EXECUTE_QUERY_MESSAGE = "올바르지 않은 executeQuery 실행입니다.";
    private static ChessJDBCTemplate chessJDBCTemplate = null;

    private ChessJDBCTemplate() {
    }

    public static ChessJDBCTemplate getInstance() {
        if (chessJDBCTemplate == null) {
            chessJDBCTemplate = new ChessJDBCTemplate();
        }
        return chessJDBCTemplate;
    }

    public void executeUpdate(String query) {
        try (Connection connection = ChessJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(ERROR_INVALID_EXECUTE_UPDATE_MESSAGE);
        }
    }

    public void executeUpdate(String query, List<Object> queryValues) {
        try (Connection connection = ChessJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = createPreparedStatement(connection, query, queryValues)) {

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(ERROR_INVALID_EXECUTE_UPDATE_MESSAGE);
        }
    }

    private PreparedStatement createPreparedStatement(Connection connection, String query, List<Object> queryValues)
            throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(query);
        for (int index = 0; index < queryValues.size(); index++) {
            pstmt.setObject(index + 1, queryValues.get(index));
        }
        return pstmt;
    }

    public List<Map<String, Object>> executeQuery(String query) {
        try (Connection connection = ChessJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            return makeResults(rs);
        } catch (SQLException e) {
            throw new DataAccessException(ERROR_INVALID_EXECUTE_QUERY_MESSAGE);
        }
    }

    public List<Map<String, Object>> executeQuery(String query, List<Object> queryValues) {
        try (Connection connection = ChessJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = createPreparedStatement(connection, query, queryValues);
             ResultSet rs = pstmt.executeQuery()) {

            return makeResults(rs);
        } catch (SQLException e) {
            throw new DataAccessException(ERROR_INVALID_EXECUTE_QUERY_MESSAGE);
        }
    }

    private List<Map<String, Object>> makeResults(ResultSet rs) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();

        while (rs.next()) {
            Map<String, Object> resultRow = makeResultRow(rs);
            results.add(resultRow);
        }
        return results;
    }

    private Map<String, Object> makeResultRow(ResultSet rs) throws SQLException {
        Map<String, Object> resultRow = new HashMap<>();

        ResultSetMetaData rsMetaData = rs.getMetaData();
        for (int index = 1; index <= rsMetaData.getColumnCount(); index++) {
            String columnName = rsMetaData.getColumnName(index);
            resultRow.put(columnName, rs.getObject(columnName));
        }
        return resultRow;
    }
}
