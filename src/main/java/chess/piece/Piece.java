package chess.piece;

public abstract class Piece {

    private final Team team;

    protected Piece(final Team team) {
        this.team = team;
    }

    public abstract void move();

    public boolean isPawn() {
        return false;
    }

    ;

    public boolean isKing() {
        return false;
    }

    ;

    public boolean isQueen() {
        return false;
    }

    ;

    public boolean isRook() {
        return false;
    }

    ;

    public boolean isKnight() {
        return false;
    }

    ;

    public boolean isBishop() {
        return false;
    }

    ;

    public boolean isEmpty() {
        return false;
    }

    public boolean isWhite() {
        return team == Team.WHITE;
    }

    public boolean isBlack() {
        return team == Team.BLACK;
    }
}
