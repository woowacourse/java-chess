package chess.domain;

public class Square {
    private final Position position;
    private Piece piece;

    private Square(final Position position, final Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public static Square of(final Position position, final Piece piece) {
        return new Square(position, piece);
    }

    public boolean movePiece(final Position target) {
        return false;
    }
}
