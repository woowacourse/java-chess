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

        int direction = (teamColor == TeamColor.WHITE) ? 1 : -1;

        // 두 칸 전진 가능
        if (target.rowValue() - start.rowValue() == 2 * direction) {
            if (!start.canMoveUp(2 * direction)) {
                return false;
            }
            if(moveCount != 0) {
                throw new IllegalArgumentException("폰은 처음에만 두 칸을 이동할 수 있습니다.");
            }
            Position moved = start.moveUp(2 * direction);
            return moved.equals(target);
        }

        if(piecesOnRoute.size() != 1) {
            return false;
        }
        // 한 칸 전진 가능
        if (piecesOnRoute.getLast().isEmptyPiece()) {
            if (!start.canMoveUp(1 * direction)) {
                return false;
            }
            Position moved = start.moveUp(1 * direction);
            return moved.equals(target);
        }

        // 적이 있을 때
        for (Movement takeMovement : pawnTakeMovements) {
            if (!this.isOtherTeam(piecesOnRoute.getLast())) {
                return false;
            }
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
    public boolean isEmptyPiece() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }


}
