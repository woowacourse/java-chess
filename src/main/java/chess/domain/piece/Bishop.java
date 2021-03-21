package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;

public class Bishop extends Piece {
    private static final Bishop BLACK_BISHOP = new Bishop(Owner.BLACK);
    private static final Bishop WHITE_BISHOP = new Bishop(Owner.WHITE);

    public Bishop(final Owner owner) {
        super(
                owner,
                new Score(3.0d),
                Direction.diagonalDirections(),
                7,
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

    @Override
    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        return true;
    }
}
