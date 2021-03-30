package chess.domain.piece.king;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.MaxDistance;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;

import java.util.List;

public abstract class King extends Piece {

    private King(final Owner owner, final Score score, final List<Direction> directions, final MaxDistance maxDistance) {
        super(owner, score, directions, maxDistance);
    }

    protected King(final Owner owner, final Score score, final List<Direction> directions) {
        this(owner, score, directions, MaxDistance.KING);
    }

    protected King(final Owner owner) {
        this(owner, new Score(0.0d), Direction.allDirections());
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
    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        return true;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}