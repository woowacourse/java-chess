package domain.chessboard;

import domain.piece.Color;

public class ColorExistKing {

    private final Color color;
    private final boolean isExistKing;

    public ColorExistKing(Color color, boolean isExistKing) {
        this.color = color;
        this.isExistKing = isExistKing;
    }

    public Color getColor() {
        return color;
    }

    public boolean getIsExistKing() {
        return isExistKing;
    }

}
