package chess.domain.piece;

public class Square {
    private final Piece piece;

    Square() {
        piece = null;
    }

    Square(Piece piece) {
        this.piece = piece;
    }

    public boolean isFilled() {
        return piece != null;
    }
}
