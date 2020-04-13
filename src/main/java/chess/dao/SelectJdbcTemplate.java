package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.util.JDBCConnector;

public abstract class SelectJdbcTemplate {

    public Object executeQuery(String query) throws SQLException {
        try (Connection con = JDBCConnector.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)
        ) {
            setParameters(pstmt);
            try (ResultSet rs = pstmt.executeQuery()) {
                return mapRow(rs);
            }
        }
    }

    public abstract Object mapRow(final ResultSet rs) throws SQLException;

    public abstract void setParameters(final PreparedStatement pstmt) throws SQLException;

}
