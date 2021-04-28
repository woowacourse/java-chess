package chess.dao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JDBCHelper {

    public static PreparedStatement createPreparedStatement(
        final Connection connection, final String query, final List<Object> params)
        throws SQLException {

        final PreparedStatement pstmt = connection
            .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < params.size(); i++) {
            pstmt.setObject(i + 1, params.get(i));
        }
        return pstmt;
    }

    public static Long getGeneratedKey(final PreparedStatement pstmt) throws SQLException {
        try (
            final ResultSet resultSet = pstmt.getGeneratedKeys()
        ) {
            resultSet.next();
            return resultSet.getLong(1);
        }
    }
}
