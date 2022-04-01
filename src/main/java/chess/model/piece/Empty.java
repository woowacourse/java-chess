package chess.model.piece;

import chess.model.Board;
import chess.model.square.Direction;
import chess.model.square.Square;
import java.util.List;

public class Empty extends AbstractPiece {

    public Empty() {
        super(Color.EMPTY);
    }

    @Override
    public String name() {
        return ".";
    }

    @Override
    public boolean movable(Square source, Square target) {
        return false;
    }

    @Override
    public boolean canMoveWithoutObstacle(Board board, Square source, Square target) {
        return false;
    }

    @Override
    public List<Direction> getDirection() {
        return null;
    }

    @Override
    public boolean isNotEmpty() {
        return false;
    }

    @Override
    public double getPoint() {
        return 0;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
