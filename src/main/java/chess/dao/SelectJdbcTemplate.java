package chess.dao;

import chess.domain.ChessGameException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SelectJdbcTemplate {
    private static SelectJdbcTemplate template;

    private SelectJdbcTemplate() {
    }

    public static SelectJdbcTemplate getInstance() {
        if (template == null) {
            template = new SelectJdbcTemplate();
        }
        return template;
    }

    public <T> T executeQuery(String query, List<String> parameters, Function<ResultSet, T> function) {
        try (Connection con = DataBaseConnector.getConnection();
             PreparedStatement pstmt = createPreparedStatement(con, query, parameters);
             ResultSet rs = pstmt.executeQuery()) {
            return function.getResult(rs);
        } catch (SQLException e) {
            throw new ChessGameException(e.getMessage());
        }
    }

    private PreparedStatement createPreparedStatement(Connection con, String query, List<String> parameters) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(query);
        for (int i = 1; i <= parameters.size(); i++) {
            pstmt.setString(i, parameters.get(i - 1));
        }
        return pstmt;
    }

    @FunctionalInterface
    interface Function<ResultSet, T> {
        T getResult(ResultSet resultSet) throws SQLException;
    }
}
