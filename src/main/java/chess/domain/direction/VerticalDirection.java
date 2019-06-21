package chess.domain.direction;

import chess.domain.Board;
import chess.domain.Position;

public class VerticalDirection implements Direction {
    private VerticalDirection () {}

    private static class VerticalDirectionHolder {
        private static final VerticalDirection instance = new VerticalDirection();
    }

    public static VerticalDirection getInstance () {
        return VerticalDirectionHolder.instance;
    }

    @Override
    public Position simulateUnitMove(Board board, Position position, boolean isReverseDirection) {
        if(isReverseDirection){
            Position newPosition = position.movePosition(-1,0);
            board.checkUnOccupiedPosition(newPosition.toString());
            return newPosition;
        }

        Position newPosition = position.movePosition(1,0);
        board.checkUnOccupiedPosition(newPosition.toString());
        return newPosition;
    }

    @Override
    public int matchMoveCount(int rowDifference, int columnDifference) {
        return rowDifference;
    }
}
