package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;

public class King extends Piece {
    private static final int ABLE_DISTANCE_TO_MOVE = 1;

    private static final King BLACK_KING = new King(Owner.BLACK);
    private static final King WHITE_KING = new King(Owner.WHITE);

    public King(final Owner owner) {
        super(owner, new Score(0.0d), Direction.allDirections());
    }

    public static King getInstanceOf(final Owner owner) {
        if (owner.equals(Owner.BLACK)) {
            return BLACK_KING;
        }

        if (owner.equals(Owner.WHITE)) {
            return WHITE_KING;
        }

        throw new IllegalArgumentException("Invalid King");
    }

    @Override
    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        return true;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public int getMaxDistance() {
        return ABLE_DISTANCE_TO_MOVE;
    }
}