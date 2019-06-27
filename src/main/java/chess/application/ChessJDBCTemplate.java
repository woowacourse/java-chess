package chess.application;

import chess.application.chessround.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public <T> void executeUpdate(String query, PreparedStatementCallback<T> callback) {
        try (Connection connection = ChessJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            callback.doInPreparedStatement(pstmt);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(ERROR_INVALID_EXECUTE_UPDATE_MESSAGE);
        }
    }

    public <T> List<T> executeQuery(String query, RowMapper<T> mapper) {
        try (Connection connection = ChessJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            return makeRows(mapper, pstmt);
        } catch (SQLException e) {
            throw new DataAccessException(ERROR_INVALID_EXECUTE_QUERY_MESSAGE);
        }
    }

    public <T> List<T> executeQuery(String query, PreparedStatementCallback<T> callback, RowMapper<T> mapper) {
        try (Connection connection = ChessJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            callback.doInPreparedStatement(pstmt);

            return makeRows(mapper, pstmt);
        } catch (SQLException e) {
            throw new DataAccessException(ERROR_INVALID_EXECUTE_QUERY_MESSAGE);
        }
    }

    private <T> List<T> makeRows(RowMapper<T> mapper, PreparedStatement pstmt) {
        try (ResultSet rs = pstmt.executeQuery()) {
            List<T> rows = new ArrayList<>();
            for (int rowNum = 1; rs.next(); rowNum++) {
                rows.add(mapper.mapRow(rs, rowNum));
            }
            return rows;
        } catch (SQLException e) {
            throw new DataAccessException(ERROR_INVALID_EXECUTE_QUERY_MESSAGE);
        }
    }
}
