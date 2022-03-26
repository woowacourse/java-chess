package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import java.util.List;

public final class Bishop extends Piece{
    public Bishop(Color color, Square square) {
        super(color, square);
    }

    //TODO: 이 부분 퀸, 룩, 비숍이 중복임.
    @Override
    public boolean movable(Piece targetPiece) {
        if (!targetPiece.isAlly(this) && canMoveTo(targetPiece)) {
            return true;
        }
        return false;
    }

    @Override
    public Point getPoint() {
        return Point.BISHOP;
    }

    private boolean canMoveTo(Piece target) {
        try {
            return direction().contains(this.findDirectionTo(target));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String getLetter() {
        return "b";
    }

    //TODO: 이 부분은 EMPTY를 제외한 모든 Piece가 가짐.
    private List<Direction> direction() {
        return List.of(Direction.SOUTHEAST, Direction.NORTHEAST, Direction.SOUTHWEST, Direction.NORTHWEST);
    }
}
