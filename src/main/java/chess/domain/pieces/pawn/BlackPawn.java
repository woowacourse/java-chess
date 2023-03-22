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
    protected void validateDiagonal(final Direction movableDirection, final List<Piece> otherPieces) {
        Piece otherPiece = otherPieces.get(OTHER_PIECE_INDEX);

        if (validateAllyKill(movableDirection, otherPiece)) {
            throw new IllegalArgumentException(INVALID_MOVE_DIAGONAL);
        }
    }

    private boolean validateAllyKill(final Direction movableDirection, final Piece otherPiece) {
        return movableDirection != Direction.DOWN && otherPiece.isSameTeam(this);
    }
}
