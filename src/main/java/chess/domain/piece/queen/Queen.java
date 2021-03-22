package chess.domain.piece.queen;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;

public abstract class Queen extends Piece {

    private static final int ABLE_DISTANCE_TO_MOVE = 7;

    public Queen(final Owner owner) {
        super(owner, new Score(9.0d), Direction.allDirections());
    }

    public static Queen getInstanceOf(final Owner owner) {
        if (owner.equals(Owner.BLACK)) {
            return BlackQueen.getInstance();
        }

        if (owner.equals(Owner.WHITE)) {
            return WhiteQueen.getInstance();
        }

        throw new IllegalArgumentException("Invalid Queen");
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
