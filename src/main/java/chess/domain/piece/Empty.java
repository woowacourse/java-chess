package chess.domain.piece;

import chess.domain.Point;

import java.util.Map;

public class Empty extends Piece {

    public Empty(Team team) {
        super(Type.EMPTY, team);
    }

    @Override
    public boolean isMovable(Point departure, Point destination, final Map<Point, Piece> board) {
        return false;
    }
}
