package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;

import java.sql.*;

public class PieceDao {

    private final DataSource dataSource;

    public PieceDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(final Piece piece) throws ClassNotFoundException, SQLException {
        jdbcContextWithStatementStrategy(
                new StatementStrategy() {
                    @Override
                    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                        PreparedStatement ps;
                        ps = c.prepareStatement(
                                "insert into pieces(id, team, name) values(?,?,?)");
                        ps.setString(1, piece.getId());
                        Team team = piece.getTeam();
                        ps.setString(2, team.toString());
                        ps.setString(3, piece.getName());
                        return ps;
                    }
                });
    }

    public Piece get(String id) throws ClassNotFoundException, SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = dataSource.getConnection();
            StatementStrategy statement = new StatementStrategy() {
                @Override
                public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                    PreparedStatement ps;

                    ps = c.prepareStatement(
                            "select * from pieces where id = ?");
                    ps.setString(1, id);
                    return ps;
                }
            };
            ps = statement.makePreparedStatement(c);
            rs = ps.executeQuery();
            rs.next();
            Piece piece = new Piece(Team.valueOf(rs.getString("team")), rs.getString("name"), null, null);
            piece.setId(id);
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

    private void jdbcContextWithStatementStrategy(StatementStrategy statement) throws SQLException, ClassNotFoundException {
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
}
