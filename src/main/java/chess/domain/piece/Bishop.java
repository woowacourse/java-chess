package chess.domain.piece;

import chess.domain.Point;

public final class Bishop extends Piece {

    private static final String name = "B";

    public Bishop(Team team) {
        super(name, team);
    }

    @Override
    public boolean isMovable(Point departure, Point destination) {
        return departure.isDiagonal(destination);
    }
}
