package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {
    public void executeUpdateWithoutPss(String query) throws SQLException {
        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.executeUpdate();
        }
    }

    public void executeUpdate(String query, PreparedStatementSetter pss) throws SQLException {
        try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ) {
            pss.setParameters(statement);
            statement.executeUpdate();
        }
    }

    public Object executeQuery(String query, RowMapper rm) throws SQLException {
        try (
            Connection con = ConnectionManager.getConnection();
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery()
        ) {
            return rm.mapRow(rs);
        }
    }

    public Object executeQueryWithPss(String query, PreparedStatementSetter pss, RowMapper rm) throws SQLException {
        try (
            Connection con = ConnectionManager.getConnection();
            PreparedStatement statement = con.prepareStatement(query)
        ) {
            pss.setParameters(statement);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return rm.mapRow(rs);
        }
    }
}
