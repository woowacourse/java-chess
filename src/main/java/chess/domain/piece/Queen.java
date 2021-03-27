package chess.domain.piece;

import chess.domain.piece.movement.Direction;
import chess.domain.piece.movement.Distance;

public class Queen extends Piece {
    private static final Queen BLACK_QUEEN = new Queen(Owner.BLACK);
    private static final Queen WHITE_QUEEN = new Queen(Owner.WHITE);

    public Queen(final Owner owner) {
        super(
                owner,
                Score.QUEEN,
                Direction.allDirections(),
                Distance.SEVEN,
                Symbol.QUEEN
        );
    }

    public static Queen getInstanceOf(final Owner owner) {
        if (owner.equals(Owner.BLACK)) {
            return BLACK_QUEEN;
        }

        if (owner.equals(Owner.WHITE)) {
            return WHITE_QUEEN;
        }

        throw new IllegalArgumentException("Invalid Queen");
    }
}
