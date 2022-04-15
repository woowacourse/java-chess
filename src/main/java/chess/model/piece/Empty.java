package chess.model.piece;

import chess.model.board.ConsoleBoard;
import chess.model.square.Direction;
import chess.model.square.Square;
import java.util.List;

public class Empty extends Piece {

    private static final double POINT = 0;

    public Empty() {
        super(Color.EMPTY);
    }

    public Empty(int id, int squareId) {
        super(id, Color.EMPTY, squareId);
    }

    @Override
    public String name() {
        return PieceType.empty.name();
    }

    @Override
    public boolean movable(Square source, Square target) {
        return false;
    }

    @Override
    public boolean canMoveWithoutObstacle(ConsoleBoard consoleBoard, Square source, Square target) {
        return false;
    }

    @Override
    public List<Square> getRoute(Square source, Square target) {
        return null;
    }

    @Override
    public boolean isNotEmpty() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public List<Direction> getDirection() {
        return null;
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
