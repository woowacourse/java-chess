package chess.domain.direction;

import chess.domain.Board;
import chess.domain.Position;

public class HorizonDirection implements Direction {
    private HorizonDirection () {}

    private static class HorizonDirectionHolder {
        private static final HorizonDirection instance = new HorizonDirection();
    }

    public static HorizonDirection getInstance () {
        return HorizonDirectionHolder.instance;
    }

    @Override
    public Position simulateUnitMove(Board board, Position position, boolean isReverseDirection) {
        if(isReverseDirection){
            Position newPosition = position.movePosition(0,-1);
            board.checkUnOccupiedPosition(newPosition.toString());
            return newPosition;
        }

        Position newPosition = position.movePosition(0,1);
        board.checkUnOccupiedPosition(newPosition.toString());
        return newPosition;
    }

    @Override
    public int matchMoveCount(int rowDifference, int columnDifference) {
        return columnDifference;
    }
}
