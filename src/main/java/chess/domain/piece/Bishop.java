package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.team.Team;

public class Bishop extends Piece {

    private Bishop(final Location location, final Team team) {
        super(location, team);
    }

    public static Bishop of(final Location location, final Team team) {
        return new Bishop(location, team);
    }

    public PieceType getPieceType() {
        return PieceType.BISHOP;
    }

    @Override
    public boolean isMovable(final Location target) {
        return location.isDiagonal(target);
    }
}
