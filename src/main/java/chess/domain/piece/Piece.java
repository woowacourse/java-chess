package chess.domain.piece;

import chess.domain.Team;

public abstract class Piece {

    protected Position position;
    private final String signature;

    protected Piece(Position position, String signature) {
        this.position = position;
        this.signature = signature;
    }

    public abstract boolean isMovable(Piece piece);

    public abstract double getScore();

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

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
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
    }

    public Team getTeam() {
        if (isBlank()) {
            return Team.BLANK;
        }
        if (isBlack()) {
            return Team.BLACK;
        }
        return Team.WHITE;
    }

    abstract public PieceType getType();

    public boolean isFirstTurn() {
        return false;
    }
}
