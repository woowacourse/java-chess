package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;

public class Knight extends Piece {
    private static final Knight BLACK_KNIGHT = new Knight(Owner.BLACK);
    private static final Knight WHITE_KNIGHT = new Knight(Owner.WHITE);

    public Knight(final Owner owner) {
        super(
                owner,
                new Score(2.5),
                Direction.knightDirections(),
                1,
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

    @Override
    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        return true;
    }
}
