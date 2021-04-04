package chess.domain.piece;

import chess.domain.piece.movement.Direction;
import chess.domain.piece.movement.Distance;

public class Rook extends Piece {
    private static final Rook BLACK_ROOK = new Rook(Owner.BLACK);
    private static final Rook WHITE_ROOK = new Rook(Owner.WHITE);

    public Rook(final Owner owner) {
        super(
                owner,
                Score.ROOK,
                Direction.straightDirections(),
                Distance.SEVEN,
                Symbol.ROOK
        );
    }

    public static Rook of(final Owner owner) {
        if (owner.isBlack()) {
            return BLACK_ROOK;
        }

        if (owner.isWhite()) {
            return WHITE_ROOK;
        }

        throw new IllegalArgumentException("Invalid Rook");
    }
}
