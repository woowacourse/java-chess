package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;

public class Bishop extends Piece {
    private static final int ABLE_DISTANCE_TO_MOVE = 7;

    private static final Bishop BLACK_BISHOP = new Bishop(Owner.BLACK);
    private static final Bishop WHITE_BISHOP = new Bishop(Owner.WHITE);

    public Bishop(final Owner owner) {
        super(owner, new Score(3.0d), Direction.diagonalDirections());
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

    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public int getMaxDistance() {
        return ABLE_DISTANCE_TO_MOVE;
    }
}
