package chess.domain.chessboard;

import chess.domain.chessboard.state.Empty;
import chess.domain.chessboard.state.PieceState;
import chess.domain.chessboard.state.piece.Piece;

public final class Square {
    private final PieceState pieceState;

    public Square() {
        pieceState = new Empty();
    }

    public Square(final Piece piece) {
        pieceState = piece;
    }

    public boolean isEmpty() {
        return pieceState.isEmpty();
    }

    public PieceState getPieceState() {
        return pieceState;
    }
}
