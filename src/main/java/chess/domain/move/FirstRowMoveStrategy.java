package chess.domain.move;

import chess.domain.piece.Team;
import chess.domain.piece.Piece;

public abstract class FirstRowMoveStrategy extends MoveStrategy {

    @Override
    protected boolean isTargetPositionMovable(Piece targetPiece, Team team) {
        if (!targetPiece.isBlank()) {
            return !targetPiece.isSameTeam(team);
        }
        return true;
    }
}
