package chess.dao;

import chess.dao.jdbc.JdbcContext;
import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PieceDao {
    private final JdbcContext<Position, Piece> jdbcContext;

    PieceDao(JdbcContext<Position, Piece> jdbcContext) {
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

    public void add(final Piece piece, final Position position) {
        List<String> valuesForSql = new ArrayList<>();
        Team team = piece.getTeam();
        valuesForSql.add(team.toString());
        valuesForSql.add(piece.getName());
        valuesForSql.add(position.toString());
        updateSql("insert into pieces(team, name, position) values(?,?,?)", valuesForSql);

    }

    public Map<Position, Piece> getAll() {
        return jdbcContext.queryObjects(c -> c.prepareStatement("select * from pieces"));
    }

    Piece getByName(String name) {
        return jdbcContext.queryObject(c -> {
            PreparedStatement ps = c.prepareStatement("select * from pieces where name = ?");
            ps.setString(1, name);
            return ps;
        });
    }

    void deleteAll() {
        updateSql("delete from pieces", new ArrayList<>());
    }

    private void updateSql(String sql, List<String> valuesForSql) {
        jdbcContext.updateWithStatementStrategy(
                c -> {
                    PreparedStatement ps;
                    ps = c.prepareStatement(sql);
                    for (int i = 1; i <= valuesForSql.size(); i++) {
                        ps.setString(i, valuesForSql.get(i - 1));
                    }
                    return ps;
                });
    }
}
