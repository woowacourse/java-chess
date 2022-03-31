package chess.domain.move;

import chess.domain.piece.Team;
import chess.domain.piece.Piece;

public abstract class FirstRowMoveStrategy implements MoveStrategy {

    protected boolean isMovableToTarget(Piece targetPiece, Team sourceTeam) {
        if (!targetPiece.isBlank()) {
            return targetPiece.getColor() == sourceTeam.oppositeTeam();
        }
        return true;
    }
}
