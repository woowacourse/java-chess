package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.List;

public abstract class SlidingPiece extends Piece{
    protected SlidingPiece(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    @Override
    public List<Position> findMoveAblePositions(final Position source, final Position target, final Piece targetPiece) {
        validateInvalidColor(targetPiece);
        validateInvalidPosition(source, target);
        return source.calculateBetweenPoints(target);
    }

    abstract void validateInvalidPosition(final Position source, final Position target);
}
