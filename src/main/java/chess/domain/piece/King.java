package chess.domain.piece;

import static chess.domain.piece.Type.KING;

public class King implements Piece {
    private final Color color;

    public King(Color color) {
        this.color = color;
    }

    @Override
    public String getType() {
        return KING.name();
    }

    @Override
    public boolean isBlack() {
        return color == Color.BLACK;
    }
}
