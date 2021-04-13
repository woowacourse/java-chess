package chess.domain.piece.knight;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;

import java.util.List;

public abstract class Knight extends Piece {

    private static final int MAX_DISTANCE = 1;

    private Knight(final Owner owner, final Score score, final List<Direction> directions) {
        super(owner, score, directions);
    }

    protected Knight(final Owner owner) {
        this(owner, Score.KNIGHT_SCORE, Direction.knightDirections());
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
    public int maxDistance() {
        return MAX_DISTANCE;
    }

    @Override
    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        return true;
    }
}
