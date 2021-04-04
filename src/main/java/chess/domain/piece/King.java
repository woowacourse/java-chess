package chess.domain.piece;

import chess.domain.piece.movement.Direction;
import chess.domain.piece.movement.Distance;

public class King extends Piece {
    private static final King BLACK_KING = new King(Owner.BLACK);
    private static final King WHITE_KING = new King(Owner.WHITE);

    public King(final Owner owner) {
        super(
                owner,
                Score.EMPTY,
                Direction.allDirections(),
                Distance.ONE,
                Symbol.KING
        );
    }

    public static King of(final Owner owner) {
        if (owner.isBlack()) {
            return BLACK_KING;
        }

        if (owner.isWhite()) {
            return WHITE_KING;
        }

        throw new IllegalArgumentException("Invalid King");
    }

    @Override
    public boolean isKing() {
        return true;
    }
}