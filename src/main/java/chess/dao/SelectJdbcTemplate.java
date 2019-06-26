package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class SelectJdbcTemplate<T> {
    public T executeQuery(String query, List<String> parameters) throws SQLException {
        Connection con = DataBaseConnector.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        setParameter(pstmt, parameters);
        ResultSet rs = pstmt.executeQuery();
        T result = getResult(rs);
        return result;
    }

    public void setParameter(PreparedStatement pstmt, List<String> parameters) throws SQLException {
        for (int i = 1; i <= parameters.size(); i++) {
            pstmt.setString(i, parameters.get(i - 1));
        }
    }

    public abstract T getResult(ResultSet resultSet) throws SQLException;
}
