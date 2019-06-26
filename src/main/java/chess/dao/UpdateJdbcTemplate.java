package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UpdateJdbcTemplate {
    public void updateQuery(String query, List<String> parameters) throws SQLException {
        Connection con = DataBaseConnector.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        setParameter(pstmt, parameters);
        pstmt.executeUpdate();
    }

    public void setParameter(PreparedStatement pstmt, List<String> parameters) throws SQLException {
        for (int i = 1; i <= parameters.size(); i++) {
            pstmt.setString(i, parameters.get(i - 1));
        }
    }
}
