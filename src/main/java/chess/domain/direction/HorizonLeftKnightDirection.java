package chess.domain.direction;

import chess.domain.Position;

public class HorizonLeftKnightDirection implements Direction {
    @Override
    public Position simulateUnitMove(Position position, boolean isReverseDirection) {
        if(isReverseDirection){
            return position.movePosition(-1,-2);
        }
        return position.movePosition(1,2);
    }
}
