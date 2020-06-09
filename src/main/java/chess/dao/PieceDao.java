package chess.dao;

import chess.domain.dto.PieceDto;
import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PieceDao {
    //check branch chess-2-applying-generic
    private final JdbcContext jdbcContext;

    PieceDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public void update(Piece piece, Position to) {
        List<String> valuesForSql = new ArrayList<>();
        Team team = piece.getTeam();
        valuesForSql.add(team.toString());
        valuesForSql.add(piece.getName());
        valuesForSql.add(to.toString());
        valuesForSql.add(piece.getId());
        updateSql("update pieces set team = ?, name = ?, position = ? where id = ?", valuesForSql);

    }

    public PieceDto get(String id) {
        return querySql("select * from pieces where id = ?", Collections.singletonList(id));
    }

    public void deleteAll() {
        updateSql("delete from pieces", new ArrayList<>());
    }

    void add(final Piece piece, final Position position) {
        List<String> valuesForSql = new ArrayList<>();
        valuesForSql.add(piece.getId());
        Team team = piece.getTeam();
        valuesForSql.add(team.toString());
        valuesForSql.add(piece.getName());
        valuesForSql.add(position.toString());
        updateSql("insert into pieces(id, team, name, position) values(?,?,?,?)", valuesForSql);

    }

    private void updateSql(String sql, List<String> valuesForSql) {
        jdbcContext.updateWithStatementStrategy(
                new StatementStrategy() {
                    @Override
                    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                        PreparedStatement ps;
                        ps = c.prepareStatement(sql);
                        for (int i = 1; i <= valuesForSql.size(); i++) {
                            ps.setString(i, valuesForSql.get(i - 1));
                        }
                        return ps;
                    }
                });
    }

    private PieceDto querySql(String sql, List<String> values) {
        return jdbcContext.queryWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                PreparedStatement ps;

                ps = c.prepareStatement(sql);
                for (int i = 1; i <= values.size(); i++) {
                    ps.setString(i, values.get(i - 1));
                }
                return ps;
            }
        });
    }
}
