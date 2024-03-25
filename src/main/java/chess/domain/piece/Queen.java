package chess.domain.piece;

import chess.domain.Point;

public final class Queen extends Piece {

    private static final String name = "Q";

    public Queen(Team team) {
        super(name, team);
    }

    @Override
    public boolean isMovable(Point departure, Point destination) {
        return departure.isStraight(destination) || departure.isDiagonalWithSlopeOfOne(destination);
    }
}
