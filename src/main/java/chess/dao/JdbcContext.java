package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcContext {
    private final DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void updateWithStatementStrategy(StatementStrategy statement) throws SQLException, ClassNotFoundException {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = dataSource.getConnection();
            ps = statement.makePreparedStatement(c);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public Piece queryWithStatementStrategy(StatementStrategy statement) throws SQLException, ClassNotFoundException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = dataSource.getConnection();
            ps = statement.makePreparedStatement(c);
            rs = ps.executeQuery();
            rs.next();
            Piece piece = new Piece(Team.valueOf(rs.getString("team")), rs.getString("name"), null, null);
            return piece;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }


        }
    }
}
