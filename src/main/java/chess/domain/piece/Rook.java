package chess.domain.piece;

import static chess.domain.piece.Type.ROOK;

public class Rook implements Piece {
    private final Color color;

    public Rook(Color color) {
        this.color = color;
    }

    @Override
    public String getType() {
        return ROOK.name();
    }

    @Override
    public boolean isBlack() {
        return color == Color.BLACK;
    }
}
