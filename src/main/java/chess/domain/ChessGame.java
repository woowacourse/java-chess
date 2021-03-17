package chess.domain;

import chess.domain.piece.CurrentPieces;

public class ChessGame {
    private final CurrentPieces currentPieces;

    public ChessGame() {
        this.currentPieces = CurrentPieces.generate();
    }

    public CurrentPieces getCurrentPieces() {
        return currentPieces;
    }
}
