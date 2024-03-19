package chess.domain.piece;

import chess.domain.Point;

public final class King extends Piece {

    private static final String name = "K";

    public King(Point point, Team team) {
        super(point, team);
    }
}
