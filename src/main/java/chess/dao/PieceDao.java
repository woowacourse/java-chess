package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;

import java.sql.*;

public class PieceDao {

    private final JdbcContext jdbcContext;

    public PieceDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public void add(final Piece piece) throws ClassNotFoundException, SQLException {
        jdbcContext.updateWithStatementStrategy(
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
        Piece piece = jdbcContext.queryWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                PreparedStatement ps;

                ps = c.prepareStatement(
                        "select * from pieces where id = ?");
                ps.setString(1, id);
                return ps;
            }
        });
        piece.setId(id);

        return piece;
    }
}
