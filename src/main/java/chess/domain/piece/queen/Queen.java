package chess.domain.piece.queen;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;

import java.util.List;

public abstract class Queen extends Piece {

    private static final int MAX_DISTANCE = 7;

    private Queen(final Owner owner, final Score score, final List<Direction> directions) {
        super(owner, score, directions);
    }

    protected Queen(final Owner owner) {
        this(owner, Score.QUEEN_SCORE, Direction.allDirections());
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
    public int maxDistance() {
        return MAX_DISTANCE;
    }

    @Override
    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        return true;
    }
}
