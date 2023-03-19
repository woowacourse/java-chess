package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;
import chess.strategy.PawnStrategy;

public class Pawn extends Piece {

    public Pawn(Team team) {
        super(Role.PAWN, team, new PawnStrategy());
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return moveStrategy.isMovable(source, target) && isCollectDirection(source, target);
    }

    private boolean isCollectDirection(Position source, Position target) {
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
