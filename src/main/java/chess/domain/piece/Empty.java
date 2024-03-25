package chess.domain.piece;

import chess.domain.Point;

public class Empty extends Piece {

    private static final String NAME = ".";

    public Empty(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(Point departure, Point destination) {
        return false;
    }
}
