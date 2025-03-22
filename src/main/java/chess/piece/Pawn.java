package chess.piece;

import chess.Movement;
import chess.Position;
import chess.TeamColor;
import java.util.List;

public class Pawn extends Piece{
    private final static Movement pawnMovement = Movement.UP;
    private final static List<Movement> pawnTakeMovements =
            List.of(Movement.LEFT_UP, Movement.RIGHT_UP);

    Pawn(TeamColor teamColor) {
        super(teamColor);
    }


    @Override
    public boolean availablePath(Position start, Position target) {
        return false;
    }

    @Override
    public List<Position> findAllRouteToTarget(Position start, Position target) {
        return List.of(target);
    }

    @Override
    public boolean canMove(Piece targetPiece, Position start, Position target) {
        if(targetPiece.isEmpty()) {
            if(!start.canMoveUp(1)) {
                return false;
            }
            Position moved = start.moveUp(1);
            return moved.equals(target);
        }

        //적이 있을때
        for (Movement takeMovement : pawnTakeMovements) {
            if(!start.canMove(takeMovement)) {
                continue;
            }

            Position moved = start.move(takeMovement);
            if(moved.equals(target)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


}
