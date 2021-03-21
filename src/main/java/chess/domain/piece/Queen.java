package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;

public class Queen extends Piece {
    private static final Queen BLACK_QUEEN = new Queen(Owner.BLACK);
    private static final Queen WHITE_QUEEN = new Queen(Owner.WHITE);

    public Queen(final Owner owner) {
        super(
                owner,
                new Score(9.0d),
                Direction.allDirections(),
                7,
                "Q"
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

    @Override
    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        return true;
    }
}
