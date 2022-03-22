package chess.domain.piece;

public abstract class Piece {

    private Position position;
    private final String signature;

    protected Piece(Position position, String signature) {
        this.position = position;
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }
}
