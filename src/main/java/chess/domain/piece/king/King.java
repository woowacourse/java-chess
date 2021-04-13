package chess.domain.piece.king;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;

import java.util.List;

public abstract class King extends Piece {

    private static final int MAX_DISTANCE = 1;

    private King(final Owner owner, final Score score, final List<Direction> directions) {
        super(owner, score, directions);
    }

    protected King(final Owner owner) {
        this(owner, Score.ZERO_SCORE, Direction.allDirections());
    }

    public static King getInstanceOf(final Owner owner) {
        if (owner.isSame(Owner.BLACK)) {
            return BlackKing.getInstance();
        }

        if (owner.isSame(Owner.WHITE)) {
            return WhiteKing.getInstance();
        }

        throw new IllegalArgumentException("Invalid King");
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