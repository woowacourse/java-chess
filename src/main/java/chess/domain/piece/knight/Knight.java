package chess.domain.piece.knight;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.MaxDistance;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;

import java.util.List;

public abstract class Knight extends Piece {

    private Knight(final Owner owner, final Score score, final List<Direction> directions, final MaxDistance maxDistance) {
        super(owner, score, directions, maxDistance);
    }

    protected Knight(final Owner owner) {
        this(owner, new Score(2.5), Direction.knightDirections(), MaxDistance.KNIGHT);
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
}
