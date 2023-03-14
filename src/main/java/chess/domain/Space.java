package chess.domain;

public class Space {

    private final Piece piece;

    public Space() {
        this(new Piece());
    }

    public Space(Piece piece) {
        this.piece = piece;
    }

    public String getPieceName() {
        return piece.getName();
    }
}
