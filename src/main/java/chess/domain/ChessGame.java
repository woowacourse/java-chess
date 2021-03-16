package chess.domain;

import chess.domain.piece.CurrentPieces;

public class ChessGame {
    private static final String START = "start";
    private static final String END = "end";

    private final CurrentPieces currentPieces;

    public ChessGame() {
        this.currentPieces = CurrentPieces.generate();
    }

    public boolean isRunning(String input) {
        if (START.equals(input)) {
            return true;
        }
        if (END.equals(input)) {
            return false;
        }
        throw new IllegalArgumentException("[ERROR] start 또는 end만 입력 가능합니다.");
    }
}
