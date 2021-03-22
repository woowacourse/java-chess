package chess.domain.piece.rook;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;

public abstract class Rook extends Piece {
    private static final int ABLE_DISTANCE_TO_MOVE = 7;

    public Rook(final Owner owner) {
        super(owner, new Score(5.0d), Direction.straightDirections());
    }

    public static Rook getInstanceOf(final Owner owner) {
        if (owner.equals(Owner.BLACK)) {
            return BlackRook.getInstance();
        }

        if (owner.equals(Owner.WHITE)) {
            return WhiteRook.getInstance();
        }

        throw new IllegalArgumentException("Invalid Rook");
    }

    @Override
    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        return true;
    }

    @Override
    public int getMaxDistance() {
        return ABLE_DISTANCE_TO_MOVE;
    }
}
