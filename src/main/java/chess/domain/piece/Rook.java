package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;

public class Rook extends Piece {
    private static final int ABLE_DISTANCE_TO_MOVE = 7;
    private static final Rook BLACK_ROOK = new Rook(Owner.BLACK);
    private static final Rook WHITE_ROOK = new Rook(Owner.WHITE);

    public Rook(final Owner owner) {
        super(owner, new Score(5.0d), Direction.straightDirections());
    }

    public static Rook getInstanceOf(final Owner owner) {
        if (owner.equals(Owner.BLACK)) {
            return BLACK_ROOK;
        }

        if (owner.equals(Owner.WHITE)) {
            return WHITE_ROOK;
        }

        throw new IllegalArgumentException("Invalid Rook");
    }

    @Override
    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        return true;
    }

    @Override
    public String getSymbol() {
        return "R";
    }

    @Override
    public int getMaxDistance() {
        return ABLE_DISTANCE_TO_MOVE;
    }
}
