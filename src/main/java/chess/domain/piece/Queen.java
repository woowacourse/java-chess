package chess.domain.piece;

import static chess.domain.piece.Type.QUEEN;

public class Queen implements Piece {
    private final Color color;

    public Queen(Color color) {
        this.color = color;
    }

    @Override
    public String getType() {
        return QUEEN.name();
    }

    @Override
    public boolean isBlack() {
        return color == Color.BLACK;
    }
}
