package chess.domain.direction;

import chess.domain.Board;
import chess.domain.Position;

public class HorizonLeftKnightDirection implements Direction {
    private HorizonLeftKnightDirection () {}

    private static class HorizonLeftKnightDirectionHolder {
        private static final HorizonLeftKnightDirection instance = new HorizonLeftKnightDirection();
    }

    public static HorizonLeftKnightDirection getInstance () {
        return HorizonLeftKnightDirectionHolder.instance;
    }

    @Override
    public Position simulateUnitMove(Board board, Position position, boolean isReverseDirection) {
        if(isReverseDirection){
            Position newPosition = position.movePosition(-1,-2);
            board.checkUnOccupiedPosition(newPosition.toString());
            return newPosition;
        }

        Position newPosition = position.movePosition(1,2);
        board.checkUnOccupiedPosition(newPosition.toString());
        return newPosition;
    }

    @Override
    public int matchMoveCount(int rowDifference, int columnDifference) {
        return rowDifference;
    }
}
