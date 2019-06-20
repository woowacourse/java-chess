package chess.domain.direction;

import chess.domain.Position;

public class VerticalDirection implements Direction {

    @Override
    public Position simulateUnitMove(Position position, boolean isReverseDirection) {
        if(isReverseDirection){
            return position.movePosition(-1,0);
        }
        return position.movePosition(1,0);
    }
}
