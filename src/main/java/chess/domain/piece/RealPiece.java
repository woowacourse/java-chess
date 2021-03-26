package chess.domain.piece;

import chess.domain.piece.strategy.PieceRange;
import chess.domain.piece.strategy.PieceStrategy;
import chess.domain.position.Notation;

public final class RealPiece implements Piece {

    private final PieceColor color;
    private final PieceStrategy pieceStrategy;

    protected RealPiece(final PieceColor color, final PieceStrategy pieceStrategy) {
        this.color = color;
        this.pieceStrategy = pieceStrategy;
    }

    @Override
    public PieceRange movableFrom(Notation notation) {
        return pieceStrategy.pieceRangeFrom(notation);
    }

    @Override
    public boolean isColor(PieceColor color) {
        return this.color.equals(color);
    }

    @Override
    public boolean isPawn() {
        return pieceStrategy.isPawn();
    }

    @Override
    public boolean isKing() {
        return pieceStrategy.isKing();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
