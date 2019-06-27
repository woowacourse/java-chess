package chess.dao;

import chess.domain.ChessGameException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UpdateJdbcTemplate {
    private static UpdateJdbcTemplate template;

    private UpdateJdbcTemplate() {
    }

    public static UpdateJdbcTemplate getInstance() {
        if (template == null) {
            template = new UpdateJdbcTemplate();
        }
        return template;
    }

    public void updateQuery(String query, List<String> parameters) {
        try (Connection con = DataBaseConnector.getConnection();
             PreparedStatement pstmt = createPreparedStatement(con, query, parameters)) {
            pstmt.executeUpdate();
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
}
