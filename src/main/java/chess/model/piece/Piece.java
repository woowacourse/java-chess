package chess.model.piece;

public abstract class Piece {
    private final Color color;
    private final Type type;

    Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public abstract boolean isValid(Movement movement);

    public String getSignature() {
        Signature signature = type.getSignature();
        if (Color.BLACK == color) {
            return signature.getBlack();
        }
        return signature.getWhite();
    }

    public boolean isType(Type type) {
        return this.type == type;
    }

    public boolean isEmpty() {
        return isType(Type.NONE);
    }

    public boolean isSameColorWith(Piece piece) {
        return color == piece.color;
    }
}
