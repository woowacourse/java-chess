package chess.domain.piece;

import static chess.domain.piece.Type.BISHOP;

public class Bishop implements Piece {
    private final Color color;

    public Bishop(Color color) {
        this.color = color;
    }

    public String getType() {
        return BISHOP.name();
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }
}
