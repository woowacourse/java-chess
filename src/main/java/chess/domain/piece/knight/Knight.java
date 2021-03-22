package chess.domain.piece.knight;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;

public abstract class Knight extends Piece {

    private static final int ABLE_DISTANCE_TO_MOVE = 1;

    public Knight(final Owner owner) {
        super(owner, new Score(2.5), Direction.knightDirections());
    }

    public static Knight getInstanceOf(final Owner owner) {
        if (owner.equals(Owner.BLACK)) {
            return BlackKnight.getInstance();
        }

        if (owner.equals(Owner.WHITE)) {
            return WhiteKnight.getInstance();
        }

        throw new IllegalArgumentException("Invalid Knight");
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
