package chess.domain.piece;

import chess.domain.Point;

import java.util.Map;

public class Empty extends Piece {

    private static final String NAME = ".";

    public Empty(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(Point departure, Point destination, final Map<Point, Piece> board) {
        return false;
    }
}
