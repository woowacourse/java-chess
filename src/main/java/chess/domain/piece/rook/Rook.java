package chess.domain.piece.rook;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;

import java.util.List;

public abstract class Rook extends Piece {

    private static final int MAX_DISTANCE = 7;

    private Rook(final Owner owner, final Score score, final List<Direction> directions) {
        super(owner, score, directions);
    }

    protected Rook(final Owner owner) {
        this(owner, Score.ROOK_SCORE, Direction.straightDirections());
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
    public int maxDistance() {
        return MAX_DISTANCE;
    }

    @Override
    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        return true;
    }
}
