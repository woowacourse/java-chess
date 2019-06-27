package chess.dao;

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

    public <T> T executeQuery(String query, List<String> parameters, Function<ResultSet, T> function) throws SQLException {
        Connection con = DataBaseConnector.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        setParameter(pstmt, parameters);
        ResultSet rs = pstmt.executeQuery();
        T result = function.getResult(rs);
        DataBaseConnector.closeConnection(con, pstmt, rs);
        return result;
    }

    public void setParameter(PreparedStatement pstmt, List<String> parameters) throws SQLException {
        for (int i = 1; i <= parameters.size(); i++) {
            pstmt.setString(i, parameters.get(i - 1));
        }
    }

    @FunctionalInterface
    interface Function<ResultSet, T> {
        T getResult(ResultSet resultSet) throws SQLException;
    }
}
