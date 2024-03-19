package chess.model.piece;

public enum Type {
    BISHOP("b"),
    ROOK("r"),
    QUEEN("q"),
    KNIGHT("n"),
    PAWN("p"),
    KING("k"),
    NONE(".");

    private final Signature signature;

    Type(String signature) {
        this.signature = new Signature(signature);
    }

    public Signature getSignature() {
        return signature;
    }
}
