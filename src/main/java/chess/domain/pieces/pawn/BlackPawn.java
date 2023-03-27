package chess.domain.pieces.pawn;

import chess.domain.Team;
import chess.domain.math.Direction;
import chess.domain.pieces.Piece;
import java.util.List;

public final class BlackPawn extends Pawn {

    public BlackPawn() {
        super(Team.BLACK, Direction.ofBlackPawn());
    }

    @Override
    protected void validateKill(final Direction correctDirection, final List<Piece> onRoutePieces) {
        Piece otherPiece = onRoutePieces.get(OTHER_PIECE_INDEX);

        if (doesKillFront(correctDirection, otherPiece)) {
            throw new IllegalArgumentException(INVALID_MOVE_STRAIGHT);
        }
        if (doesNotKillEnemy(correctDirection, otherPiece)) {
            throw new IllegalArgumentException(INVALID_MOVE_DIAGONAL);
        }
    }

    private boolean doesKillFront(final Direction correctDirection, final Piece otherPiece) {
        return correctDirection == Direction.DOWN && !otherPiece.isNeutrality();
    }

    private boolean doesNotKillEnemy(final Direction correctDirection, final Piece otherPiece) {
        return correctDirection != Direction.DOWN && isNotValidKill(otherPiece);
    }

    private boolean isNotValidKill(final Piece otherPiece) {
        return otherPiece.isAlly(this) || otherPiece.isNeutrality();
    }
}
