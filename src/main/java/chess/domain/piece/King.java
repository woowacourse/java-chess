package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.team.Team;

public class King extends Piece {

    private King(final Location location, final Team team) {
        super(location, team);
    }

    public static King of(final Location location, final Team team) {
        return new King(location, team);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }

    @Override
    public boolean isMovable(final Location target) {
        return location.isAdjacent(target);
    }
}
