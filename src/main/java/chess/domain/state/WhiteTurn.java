package chess.domain.state;

import chess.domain.grid.Grid;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public final class WhiteTurn extends Playing {
    @Override
    final GameState move(final Grid grid, final Piece sourcePiece, final Piece targetPiece) {
        validateSourcePieceIsEmpty(sourcePiece);
        validateIfWhite(sourcePiece);
        grid.move(sourcePiece, targetPiece);
        return new BlackTurn();
    }

    private void validateIfWhite(final Piece sourcePiece) {
        if (sourcePiece.color() == Color.BLACK) {
            throw new IllegalArgumentException("자신의 말만 옮길 수 있습니다.");
        }
    }
}
