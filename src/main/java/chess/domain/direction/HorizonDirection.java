package chess.domain.direction;

import chess.domain.Position;

public class HorizonDirection implements Direction{
    @Override
    public Position simulateUnitMove(Position position, boolean isReverseDirection) {
        if(isReverseDirection){
            return position.movePosition(0,-1);
        }
        return position.movePosition(0,1);
    }

    @Override
    public int matchMoveCount(int rowDifference, int columnDifference) {
        return rowDifference;
    }
}
