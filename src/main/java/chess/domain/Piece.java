package chess.domain;

public abstract class Piece {

    private Position position;
    private final char signature;

    protected Piece(Position position, char signature) {
        this.position = position;
        this.signature = signature;
    }

    public char getSignature() {
        return signature;
    }
}
