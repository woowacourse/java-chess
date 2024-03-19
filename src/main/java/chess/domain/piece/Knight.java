package chess.domain.piece;

import chess.domain.Point;

public final class Knight extends Piece {

    private static final String name = "N";

    public Knight(Point point, Team team) {
        super(point, team);
    }
}
