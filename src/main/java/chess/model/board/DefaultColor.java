package chess.model.board;

import chess.model.Color;

public enum DefaultColor implements Color {

    EMPTY;

    @Override
    public boolean isWhite() {
        return false;
    }

    @Override
    public boolean isDifferent(final Color color) {
        return this != color;
    }

    @Override
    public boolean isNotEmpty() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
