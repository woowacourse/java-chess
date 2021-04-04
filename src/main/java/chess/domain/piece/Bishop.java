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
                Symbol.BISHOP
        );
    }

    public static Bishop of(final Owner owner) {
        if (owner.isBlack()) {
            return BLACK_BISHOP;
        }

        if (owner.isWhite()) {
            return WHITE_BISHOP;
        }

        throw new IllegalArgumentException("Invalid Bishop");
    }
}
