package chess.piece;

import chess.Movement;
import chess.Position;

import java.util.List;
import java.util.Map;
//그 방향으로 쭉
public class JMoving implements Moving {
    @Override
    public boolean checkCanMove(Map<Position, Piece> board, Position position, Position positionToMove, List<List<Movement>> movementss) {
        for (List<Movement> movements : movementss) {
            for (Movement curMov : movements) {
                Position curPos = position;
                while (curPos.canMove(curMov)) {
                    if(board.get(curPos).getClass() != None.class) {
                        break;
                    }
                    curPos = curPos.move(curMov);
                    if (curPos.equals(positionToMove)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
