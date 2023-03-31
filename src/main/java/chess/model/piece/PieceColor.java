package chess.model.piece;

import chess.model.Color;

public enum PieceColor implements Color {

    BLACK,
    WHITE;

    @Override
    public boolean isWhite() {
        return WHITE == this;
    }

    @Override
    public boolean isDifferent(final Color color) {
        return this != color;
    }

    @Override
    public boolean isNotEmpty() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isSameColor(final Color targetColor) {
        return this == targetColor;
    }
}
