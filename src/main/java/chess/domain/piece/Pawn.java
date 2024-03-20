package chess.domain.piece;

import static chess.domain.piece.Type.PAWN;

public class Pawn implements Piece {
    private final Color color;

    public Pawn(Color color) {
        this.color = color;
    }

    @Override
    public String getType() {
        return PAWN.name();
    }

    @Override
    public boolean isBlack() {
        return color == Color.BLACK;
    }
}
