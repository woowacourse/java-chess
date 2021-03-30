package chess.domain.piece.bishop;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.MaxDistance;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;

import java.util.List;

public abstract class Bishop extends Piece {

    private Bishop(final Owner owner, final Score score, final List<Direction> directions, final MaxDistance maxDistance) {
        super(owner, score, directions, maxDistance);
    }

    private Bishop(final Owner owner, final Score score, final List<Direction> directions) {
        this(owner, score, directions, MaxDistance.BISHOP);
    }

    public Bishop(final Owner owner) {
        this(owner, new Score(3.0d), Direction.diagonalDirections());
    }

    public static Bishop getInstanceOf(final Owner owner) {
        if (owner.equals(Owner.BLACK)) {
            return BlackBishop.getInstance();
        }

        if (owner.equals(Owner.WHITE)) {
            return WhiteBishop.getInstance();
        }

        throw new IllegalArgumentException("Invalid Bishop");
    }

    @Override
    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        return true;
    }
}
