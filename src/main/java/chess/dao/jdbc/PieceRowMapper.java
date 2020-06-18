package chess.dao.jdbc;

import chess.domain.piece.Piece;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PieceRowMapper implements RowMapper<Position, Piece> {

    @Override
    public Piece mapRow(ResultSet rs) throws SQLException {
        rs.next();
        String name = rs.getString("name");
        Team team = Team.valueOf(rs.getString("team"));
        return PieceFactory.createPiece(name, team);
    }

    @Override
    public  Map<Position, Piece> mapRows(ResultSet rs) throws SQLException {
        Map<Position, Piece> results = new HashMap<>();
        while (rs.next()) {
            Position position = Position.of(rs.getString("position"));
            String name = rs.getString("name");
            Team team = Team.valueOf(rs.getString("team"));
            Piece piece = PieceFactory.createPiece(name, team);
            results.put(position, piece);
        }
        return results;
    }
}
