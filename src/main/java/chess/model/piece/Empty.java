package chess.model.piece;

import chess.model.Board;
import chess.model.square.Direction;
import chess.model.square.Square;
import java.util.List;

public class Empty extends AbstractPiece {

    private static final String NAME = ".";
    private static final double POINT = 0;

    public Empty() {
        super(Color.EMPTY);
    }

    @Override
    public String name() {
        return NAME;
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
        return POINT;
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
