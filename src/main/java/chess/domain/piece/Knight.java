package chess.domain.piece;

import chess.domain.piece.movement.Direction;
import chess.domain.piece.movement.Distance;

public class Knight extends Piece {
    private static final Knight BLACK_KNIGHT = new Knight(Owner.BLACK);
    private static final Knight WHITE_KNIGHT = new Knight(Owner.WHITE);

    public Knight(final Owner owner) {
        super(
                owner,
                Score.KNIGHT,
                Direction.knightDirections(),
                Distance.ONE,
                Symbol.KNIGHT
        );
    }

    public static Knight of(final Owner owner) {
        if (owner.isBlack()) {
            return BLACK_KNIGHT;
        }

        if (owner.isWhite()) {
            return WHITE_KNIGHT;
        }

        throw new IllegalArgumentException("Invalid Knight");
    }
}
