package chess.dao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JDBCHelper {

    public static PreparedStatement createPreparedStatement(
        final Connection connection, final String query, final List<String> params)
        throws SQLException {

        final PreparedStatement pstmt = connection.prepareStatement(query);
        for (int i = 0; i < params.size(); i++) {
            pstmt.setString(i + 1, params.get(i));
        }
        return pstmt;
    }
}
