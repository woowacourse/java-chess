package chess.dao;

import chess.dao.jdbc.JdbcContext;
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
    private final JdbcContext<PieceDto> jdbcContext;

    PieceDao(JdbcContext<PieceDto> jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public void update(Piece piece, Position from, Position to) {
        List<String> valuesForSql = new ArrayList<>();
        Team team = piece.getTeam();
        valuesForSql.add(team.toString());
        valuesForSql.add(piece.getName());
        valuesForSql.add(to.toString());
        valuesForSql.add(from.toString());
        updateSql("update pieces set team = ?, name = ?, position = ? where position = ?", valuesForSql);

    }

    public PieceDto getByName(String name) {
        return querySql("select * from pieces where name = ?", Collections.singletonList(name));
    }

    public void add(final Piece piece, final Position position) {
        List<String> valuesForSql = new ArrayList<>();
        Team team = piece.getTeam();
        valuesForSql.add(team.toString());
        valuesForSql.add(piece.getName());
        valuesForSql.add(position.toString());
        updateSql("insert into pieces(team, name, position) values(?,?,?)", valuesForSql);

    }

    void deleteAll() {
        updateSql("delete from pieces", new ArrayList<>());
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
        return jdbcContext.queryObject(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement(sql);
                for (int i = 1; i <= values.size(); i++) {
                    ps.setString(i, values.get(i - 1));
                }
                return ps;
            }
        });
    }

    public List<PieceDto> getAll() {
        return jdbcContext.queryObjects(new StatementStrategy() {
            String sql = "select * from pieces";
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                return c.prepareStatement(sql);
            }
        });
    }
}
