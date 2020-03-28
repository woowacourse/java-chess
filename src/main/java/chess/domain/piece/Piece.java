package chess.domain.piece;

public abstract class Piece {
    private char representation;

    public Piece(char representation) {
        this.representation = representation;
    }

    public char getRepresentation() {
        return representation;
    }
}
