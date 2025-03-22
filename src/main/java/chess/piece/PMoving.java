package chess.piece;

import chess.Movement;
import chess.Position;

import java.util.List;
import java.util.Map;

public class PMoving implements Moving {

    @Override
    public boolean checkCanMove(Map<Position, Piece> board, Position position, Position positionToMove, List<List<Movement>> movementss) {
        System.out.println(movementss.size());
        if(position.canMove(movementss.get(0).getFirst()) &&
                position.move(movementss.get(0).getFirst()).equals(positionToMove) &&
                board.get(position.move(movementss.get(0).getFirst())).getClass() == None.class) {
            return true;
        }
        if(position.canMove(movementss.get(1).getFirst()) &&
                position.move(movementss.get(1).getFirst()).equals(positionToMove) &&
                board.get(position.move(movementss.get(1).getFirst())).getClass() != None.class) {
            return true;
        }
        if(position.canMove(movementss.get(2).getFirst()) &&
                position.move(movementss.get(2).getFirst()).equals(positionToMove) &&
                board.get(position.move(movementss.get(2).getFirst())).getClass() != None.class) {
            return true;
        }
        if(movementss.size() == 4) {
            List<Movement> movements = movementss.get(3);
            Position curPos = position;
            for(int i = 0; i < 2; i ++) {
                System.out.println(curPos);
                if(curPos.canMove(movements.get(i)) && board.get(curPos).getClass() == None.class) {
                    curPos = curPos.move(movements.get(i));
                }
                if(curPos.equals(positionToMove)) {
                    return true;
                }
            }
        }
        return false;
    }
}
