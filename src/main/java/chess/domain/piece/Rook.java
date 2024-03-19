package chess.domain.piece;

import chess.domain.Point;

public final class Rook extends Piece {

    private static final String name = "R";

    public Rook(Point point, Team team) {
        super(point, team);
    }
}
