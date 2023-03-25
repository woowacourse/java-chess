package chess.domain.piece;

import chess.domain.board.Position;
import chess.strategy.MoveStrategy;

public class Pawn extends Piece {

    public Pawn(Team team) {
        super(Role.PAWN, team);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        MoveStrategy moveStrategy = role.getMoveStrategy();
        return moveStrategy.isMovable(source, target) && isCorrectDirection(source, target);
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
}
