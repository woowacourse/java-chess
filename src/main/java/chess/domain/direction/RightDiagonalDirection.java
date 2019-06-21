package chess.domain.direction;

import chess.domain.Board;
import chess.domain.Position;

public class RightDiagonalDirection implements Direction{
    private RightDiagonalDirection() {}

    private static class RithtDiagonalDirectionHolder {
        private static final RightDiagonalDirection instance = new RightDiagonalDirection();
    }

    public static RightDiagonalDirection getInstance () {
        return RithtDiagonalDirectionHolder.instance;
    }

    @Override
    public Position simulateUnitMove(Board board, Position position, boolean isReverseDirection) {
        if(isReverseDirection){
            Position newPosition = position.movePosition(-1,-1);
            board.checkUnOccupiedPosition(newPosition.toString());
            return newPosition;
        }

        Position newPosition = position.movePosition(1,1);
        board.checkUnOccupiedPosition(newPosition.toString());
        return newPosition;
    }

    @Override
    public int matchMoveCount(int rowDifference, int columnDifference) {
        return rowDifference;
    }
}
