package chess.domain.piece;

public abstract class Piece {

    protected Position position;
    private final String signature;

    protected Piece(Position position, String signature) {
        this.position = position;
        this.signature = signature;
    }

    public abstract void move(Piece piece);

    public boolean isBlank() {
        return false;
    }

    public boolean isEnemy(String signature) {
        return Character.isLowerCase(getSignature().charAt(0)) != Character.isLowerCase(signature.charAt(0));
    }

    public Position getPosition() {
        return position;
    }

    public String getSignature() {
        return signature;
    }
}
