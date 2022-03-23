package chess.domain.piece;

public abstract class Piece {
    protected static final String INVALID_MOVEMENT_EXCEPTION_MESSAGE = "이동이 불가능한 위치입니다.";

    protected Position position;
    private final String signature;

    protected Piece(Position position, String signature) {
        this.position = position;
        this.signature = signature;
    }

    public abstract boolean move(Piece piece);

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
