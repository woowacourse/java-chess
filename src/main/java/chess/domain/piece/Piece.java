package chess.domain.piece;

public abstract class Piece {
    private final String name;

    public Piece(final boolean isBlack) {
        this.name = "";
    }

    public String getName() {
        return name;
    }
}
