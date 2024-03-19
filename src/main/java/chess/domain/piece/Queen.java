package chess.domain.piece;

import chess.domain.Point;

public final class Queen extends Piece {

    private static final String name = "Q";

    public Queen(Point point, Team team) {
        super(point, team);
    }
}

