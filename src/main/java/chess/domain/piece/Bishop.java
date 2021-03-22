package chess.domain.piece;

import chess.domain.piece.movement.Direction;
import chess.domain.piece.movement.Distance;

public class Bishop extends Piece {
    private static final Bishop BLACK_BISHOP = new Bishop(Owner.BLACK);
    private static final Bishop WHITE_BISHOP = new Bishop(Owner.WHITE);

    public Bishop(final Owner owner) {
        super(
                owner,
                Score.BISHOP,
                Direction.diagonalDirections(),
                Distance.SEVEN,
                "B"
        );
    }

    public static Bishop getInstanceOf(final Owner owner) {
        if (owner.equals(Owner.BLACK)) {
            return BLACK_BISHOP;
        }

        if (owner.equals(Owner.WHITE)) {
            return WHITE_BISHOP;
        }

        throw new IllegalArgumentException("Invalid Bishop");
    }
}
