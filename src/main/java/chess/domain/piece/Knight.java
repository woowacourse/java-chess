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
                "N"
        );
    }

    public static Knight getInstanceOf(final Owner owner) {
        if (owner.equals(Owner.BLACK)) {
            return BLACK_KNIGHT;
        }

        if (owner.equals(Owner.WHITE)) {
            return WHITE_KNIGHT;
        }

        throw new IllegalArgumentException("Invalid Knight");
    }
}
