package chess.domain.piece;

import static chess.domain.piece.Type.KNIGHT;

public class Knight implements Piece {
    private final Color color;

    public Knight(Color color) {
        this.color = color;
    }

    @Override
    public String getType() {
        return KNIGHT.name();
    }

    @Override
    public boolean isBlack() {
        return color == Color.BLACK;
    }
}
