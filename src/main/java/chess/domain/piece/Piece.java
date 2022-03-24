package chess.domain.piece;

public abstract class Piece {

    protected Position position;
    protected boolean isFirstTurn;
    private final String signature;

    protected Piece(Position position, String signature) {
        this.position = position;
        this.isFirstTurn = true;
        this.signature = signature;
    }

    public abstract boolean isMovable(Piece piece);

    public boolean isBlank() {
        return false;
    }

    public boolean isEnemy(String signature) {
        return !signature.equals(".") && Character.isLowerCase(getSignature().charAt(0)) != Character.isLowerCase(signature.charAt(0));
    }

    public boolean isKnight() {
        return false;
    }

    public Position getPosition() {
        return position;
    }

    public String getSignature() {
        return signature;
    }

    public void updatePosition(Position position) {
        this.position = position;
        this.isFirstTurn = false;
    }
}
