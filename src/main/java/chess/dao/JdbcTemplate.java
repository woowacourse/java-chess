package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {
    public void executeUpdateWithoutPss(String sql) throws SQLException {
        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.executeUpdate();
        }
    }

    public void executeUpdate(String sql, PreparedStatementSetter pss) throws SQLException {
        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            pss.setParameters(statement);
            statement.executeUpdate();
        }
    }

    public Object executeQuery(String sql, RowMapper rm) throws SQLException {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            return rm.mapRow(rs);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
