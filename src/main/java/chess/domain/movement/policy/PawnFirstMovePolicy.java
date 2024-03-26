package chess.domain.movement.policy;

import static chess.domain.board.InitialPiecePosition.BLACK_PAWN;
import static chess.domain.board.InitialPiecePosition.WHITE_PAWN;

import chess.domain.piece.Color;
import chess.domain.position.Position;

public class PawnFirstMovePolicy implements Policy {

    @Override
    public boolean isSatisfied(final Color color, final Position currentPosition, final boolean existEnemy) {
        if (color == Color.BLACK) {
            return BLACK_PAWN.isPawnFirstMove(currentPosition);
        }

        if (color == Color.WHITE) {
            return WHITE_PAWN.isPawnFirstMove(currentPosition);
        }

        return false;
    }
}
