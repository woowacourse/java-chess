package chess.domain.state;

import chess.domain.grid.Grid;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public final class BlackTurn extends Playing {
    @Override
    final GameState move(final Grid grid, final Piece sourcePiece, final Piece targetPiece) {
        validateSourcePieceIsEmpty(sourcePiece);
        validateIfBlack(sourcePiece);
        grid.move(sourcePiece, targetPiece);
        return new WhiteTurn();
    }

    private void validateIfBlack(final Piece sourcePiece) {
        if (sourcePiece.color() != Color.BLACK) {
            throw new IllegalArgumentException("자신의 말만 옮길 수 있습니다.");
        }
    }
}
