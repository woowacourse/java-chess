package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;

public class Knight extends Piece {
    private static final int ABLE_DISTANCE_TO_MOVE = 1;

    private static final Knight BLACK_KNIGHT = new Knight(Owner.BLACK);
    private static final Knight WHITE_KNIGHT = new Knight(Owner.WHITE);

    public Knight(final Owner owner) {
        super(owner, new Score(2.5), Direction.knightDirections());
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

    @Override
    public String getSymbol() {
        return "N";
    }

    @Override
    public int getMaxDistance() {
        return ABLE_DISTANCE_TO_MOVE;
    }
}
