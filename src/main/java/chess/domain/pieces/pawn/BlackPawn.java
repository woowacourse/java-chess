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
    protected void validateKill(final Direction correctDirection, final List<Piece> otherPieces) {
        if (correctDirection == Direction.DOWN) {
            return;
        }

        Piece otherPiece = otherPieces.get(OTHER_PIECE_INDEX);
        if (isNotValidKill(otherPiece)) {
            throw new IllegalArgumentException(INVALID_MOVE_DIAGONAL);
        }
    }

    private boolean isNotValidKill(final Piece otherPiece) {
        return otherPiece.isAlly(this) || otherPiece.isNeutrality();
    }
}
