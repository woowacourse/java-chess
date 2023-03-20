package chess.piece.nonsliding;

import chess.Position;
import chess.piece.Color;
import chess.piece.Direction;
import chess.piece.Piece;
import chess.piece.Pieces;
import java.util.Set;

public abstract class NonSlidingPiece extends Piece {

    protected NonSlidingPiece(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    protected Set<Position> legalMovePositions(final Direction direction, final Pieces pieces) {
        final var movedPosition = position().move(direction);
        if (pieces.isBlank(movedPosition) || pieces.isOpposite(movedPosition)) {
            return Set.of(movedPosition);
        }

        return Set.of();
    }
}
