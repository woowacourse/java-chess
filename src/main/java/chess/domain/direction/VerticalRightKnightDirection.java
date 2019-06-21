package chess.domain.direction;

import chess.domain.Board;
import chess.domain.Position;

public class VerticalRightKnightDirection implements Direction {
    private VerticalRightKnightDirection () {}

    private static class VerticalRightKnightDirectionHolder {
        private static final VerticalRightKnightDirection instance = new VerticalRightKnightDirection();
    }

    public static VerticalRightKnightDirection getInstance () {
        return VerticalRightKnightDirectionHolder.instance;
    }


    @Override
    public Position simulateUnitMove(Board board, Position position, boolean isReverseDirection) {
        if(isReverseDirection){
            Position newPosition = position.movePosition(-2,-1);
            board.checkUnOccupiedPosition(newPosition.toString());
            return newPosition;
        }

        Position newPosition = position.movePosition(2,1);
        board.checkUnOccupiedPosition(newPosition.toString());
        return newPosition;
    }

    @Override
    public int matchMoveCount(int rowDifference, int columnDifference) {
        return columnDifference;
    }
}
