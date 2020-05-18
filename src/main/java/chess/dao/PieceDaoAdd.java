package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PieceDaoAdd extends PieceDao {
    private final Piece piece;
    public PieceDaoAdd(ConnectionGetter connectionGetter, Piece piece) {
        super(connectionGetter);
        this.piece = piece;
    }

    @Override
    protected PreparedStatement makeStatement(Connection c) throws SQLException {
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
