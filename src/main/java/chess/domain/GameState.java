package chess.domain;

import chess.domain.piece.Color;

public enum GameState {

    READY, WHITE_RUNNING, BLACK_RUNNING, WHITE_WIN, BLACK_WIN, FINISHED;

    public boolean isRunning() {
        return this == WHITE_RUNNING || this == BLACK_RUNNING;
    }

    public boolean isFinished() {
        return this == FINISHED;
    }

    public GameState changeTurn() {
        if (this == WHITE_RUNNING) {
            return BLACK_RUNNING;
        }
        if (this == BLACK_RUNNING) {
            return WHITE_RUNNING;
        }
        return this;
    }

    public Color color() {
        if (this == WHITE_RUNNING) {
            return Color.WHITE;
        }
        if (this == BLACK_RUNNING) {
            return Color.BLACK;
        }
        return Color.NONE;
    }
}
