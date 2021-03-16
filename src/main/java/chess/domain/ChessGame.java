package chess.domain;

import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class ChessGame {
    private Map<String, Piece> map = new HashMap<>();
    private boolean isRunning;

    public ChessGame() {
        isRunning = true;
    }

    public void endGame() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
