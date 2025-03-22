package chess.piece;

import chess.Movement;
import chess.Position;

import java.util.List;
import java.util.Map;

//주어진 경로대로
public class GMoving implements Moving {

    @Override
    public boolean checkCanMove(Map<Position, Piece> board, Position position, Position positionToMove, List<List<Movement>> movementss) {
        for (List<Movement> movements : movementss) {
            Position curPos = position;
            for (Movement curMov : movements) {
                if(!curPos.canMove(curMov) ||board.get(curPos).getClass() != None.class) {
                    break;
                }
                curPos = curPos.move(curMov);
                if (curPos.equals(positionToMove)) {
                    return true;
                }
            }
        }
        return false;
    }
}
