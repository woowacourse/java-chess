package chess.domain.piece;

import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.TeamColor;
import java.util.List;

public class Pawn extends Piece {
    private final static Movement pawnMovement = Movement.UP;
    private final static List<Movement> pawnTakeMovements =
            List.of(Movement.LEFT_UP, Movement.RIGHT_UP);

    public Pawn(TeamColor teamColor) {
        super(teamColor, PieceType.PAWN);
    }


    @Override
    public boolean availablePath(Position start, Position target) {
        return true;
    }

    @Override
    public List<Position> findAllRouteToTarget(Position start, Position target) {
        return List.of(target);
    }

    @Override
    public boolean canMove(List<Piece> piecesOnRoute, Position start, Position target) {

        if (target.rowValue() - start.rowValue() == 2) {
            if (!start.canMoveUp(2)) {
                return false;
            }
            if (start.row() != Row.TWO) {
                return false;
            }
            Position moved = start.moveUp(2);
            return moved.equals(target);
        }

        if (piecesOnRoute.getLast().isEmpty()) {
            if (!start.canMoveUp(1)) {
                return false;
            }
            Position moved = start.moveUp(1);
            return moved.equals(target);
        }

        //적이 있을때
        for (Movement takeMovement : pawnTakeMovements) {
            if (!start.canMove(takeMovement)) {
                continue;
            }

            Position moved = start.move(takeMovement);
            if (moved.equals(target)) {
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
