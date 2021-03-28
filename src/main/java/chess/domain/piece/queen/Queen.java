package chess.domain.piece.queen;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.MaxDistance;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;

import java.util.List;

public abstract class Queen extends Piece {

    private Queen(final Owner owner, final Score score, final List<Direction> directions, MaxDistance maxDistance) {
        super(owner, score, directions, maxDistance);
    }

    protected Queen(final Owner owner, final Score score, final List<Direction> directions) {
        this(owner, score, directions, MaxDistance.QUEEN);
    }

    protected Queen(final Owner owner) {
        this(owner, new Score(9.0d), Direction.allDirections());
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
}
