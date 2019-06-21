package chess.domain.direction;

import chess.domain.Board;
import chess.domain.Position;

public class HorizonRightKnightDirection implements Direction {
    private HorizonRightKnightDirection () {}

    private static class HorizonRightKnightDirectionHolder {
        private static final HorizonRightKnightDirection instance = new HorizonRightKnightDirection();
    }

    public static HorizonRightKnightDirection getInstance () {
        return HorizonRightKnightDirectionHolder.instance;
    }

    @Override
    public Position simulateUnitMove(Board board, Position position, boolean isReverseDirection) {
        if(isReverseDirection){
            Position newPosition = position.movePosition(1,-2);
            board.checkUnOccupiedPosition(newPosition.toString());
            return newPosition;
        }

        Position newPosition = position.movePosition(-1,2);
        board.checkUnOccupiedPosition(newPosition.toString());
        return newPosition;
    }

    @Override
    public int matchMoveCount(int rowDifference, int columnDifference) {
        return rowDifference * (-1);
    }
}
