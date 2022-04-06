package chess.domain.piece;

public abstract class Piece {

    protected Position position;
    protected boolean isFirstTurn;
    private final String signature;

    protected Piece(Position position, boolean first, String signature) {
        this.position = position;
        this.isFirstTurn = first;
        this.signature = signature;
    }

    protected Piece(Position position, String signature) {
        this(position, true, signature);
    }

    public static Piece create(Position position, boolean first, String name) {
        if (name.equals("blank")) {
            return new Blank(position);
        }
        if (name.contains("black")) {
            return createBlack(position, first, name);
        }
        if (name.contains("white")) {
            return createWhite(position, first, name);
        }
        throw new IllegalArgumentException("생성할 수 없는 기물입니다.");
    }

    private static Piece createBlack(Position position, boolean first, String name) {
        if (name.contains("bishop")) {
            return Bishop.createBlack(position);
        }
        if (name.contains("king")) {
            return King.createBlack(position);
        }
        if (name.contains("knight")) {
            return Knight.createBlack(position);
        }
        if (name.contains("pawn")) {
            return Pawn.createBlack(position, first);
        }
        if (name.contains("queen")) {
            return Queen.createBlack(position);
        }
        if (name.contains("rook")) {
            return Rook.createBlack(position);
        }
        throw new IllegalArgumentException("생성할 수 없는 기물입니다.");
    }

    private static Piece createWhite(Position position, boolean first, String name) {
        if (name.contains("bishop")) {
            return Bishop.createWhite(position);
        }
        if (name.contains("king")) {
            return King.createWhite(position);
        }
        if (name.contains("knight")) {
            return Knight.createWhite(position);
        }
        if (name.contains("pawn")) {
            return Pawn.createWhite(position, first);
        }
        if (name.contains("queen")) {
            return Queen.createWhite(position);
        }
        if (name.contains("rook")) {
            return Rook.createWhite(position);
        }
        throw new IllegalArgumentException("생성할 수 없는 기물입니다.");
    }

    public abstract boolean isMovable(Piece piece);

    public abstract double getScore();

    public abstract String getName();

    public boolean isBlank() {
        return false;
    }

    public boolean isEnemy(String signature) {
        return Character.isLowerCase(getSignature().charAt(0)) != Character.isLowerCase(
                signature.charAt(0));
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isBlack() {
        return Character.isUpperCase(signature.charAt(0));
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

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isFirstTurn() {
        return isFirstTurn;
    }
}
