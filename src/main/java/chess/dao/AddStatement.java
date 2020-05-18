package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStatement implements StatementStrategy {
    private final Piece piece;
    public AddStatement(Piece piece) {
        this.piece = piece;
    }

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
}
