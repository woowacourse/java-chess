package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;
import chess.dto.BoardSnapshot;
import chess.strategy.PawnStrategy;

public class Pawn extends Piece {

    public Pawn(Team team) {
        super(Role.PAWN, team, new PawnStrategy());
    }

    @Override
    public boolean canMove(Position source, Position target, BoardSnapshot boardSnapshot) {
        return moveStrategy.isMovable(source, target) && canAttack(source, target, boardSnapshot);
    }

    private boolean canAttack(Position source, Position target, BoardSnapshot boardSnapshot) {
        return isCorrectDirection(source, target)
                && hasNotCollision(source, target, boardSnapshot)
                && isCorrectMove(source, target, boardSnapshot);
    }

    private boolean isCorrectDirection(Position source, Position target) {
        if (isWhitePawnReverseDirection(source, target) || isBlackPawnReverseDirection(source, target)) {
            return false;
        }
        return true;
    }

    private boolean isWhitePawnReverseDirection(Position source, Position target) {
        return team == Team.WHITE && source.isOverThanYTo(target);
    }

    private boolean isBlackPawnReverseDirection(Position source, Position target) {
        return team == Team.BLACK && target.isOverThanYTo(source);
    }

    private boolean isCorrectMove(Position source, Position target, BoardSnapshot boardSnapshot) {
        Piece targetPiece = boardSnapshot.findByPosition(target);
        if (source.isSameXAs(target) && !targetPiece.isRoleOf(Role.EMPTY)) {
            return false;
        }
        return source.isSameXAs(target) || !targetPiece.isRoleOf(Role.EMPTY);
    }
}
