package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PieceDao {

    //check branch chess-2-applying-generic
    private final JdbcContext jdbcContext;

    public PieceDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public void add(final Piece piece) throws ClassNotFoundException, SQLException {
        List<String> valuesForSql = new ArrayList<>();
        valuesForSql.add(piece.getId());
        Team team = piece.getTeam();
        valuesForSql.add(team.toString());
        valuesForSql.add(piece.getName());
        updateSql("insert into pieces(id, team, name) values(?,?,?)", valuesForSql);

    }

    private void updateSql(String sql, List<String> valuesForSql) throws SQLException, ClassNotFoundException {
        jdbcContext.updateWithStatementStrategy(
                new StatementStrategy() {
                    @Override
                    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                        PreparedStatement ps;
                        ps = c.prepareStatement(sql);
                        for (int i = 1; i <= valuesForSql.size(); i++) {
                            ps.setString(i, valuesForSql.get(i-1));
                        }
                        return ps;
                    }
                });
    }

    public Piece get(String id) throws ClassNotFoundException, SQLException {
        Piece piece = querySql("select * from pieces where id = ?", Collections.singletonList(id));
        piece.setId(id);

        return piece;
    }

    private Piece querySql(String sql, List<String> values) throws SQLException, ClassNotFoundException {
        return jdbcContext.queryWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                PreparedStatement ps;

                ps = c.prepareStatement(sql);
                for (int i = 1; i <= values.size(); i++) {
                    ps.setString(i,  values.get(i-1));
                }
                return ps;
            }
        });
    }
}
