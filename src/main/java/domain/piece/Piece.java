package domain.piece;

public abstract class Piece {
    private final Camp camp;

    public Piece(Camp camp) {
        this.camp = camp;
    }

    public boolean isWhite() {
        return camp.equals(Camp.WHITE);
    }
    public boolean isPawn() {
        return false;
    };
    public boolean isRook() {
        return false;
    };
    public boolean isKnight() {
        return false;
    };
    public boolean isBishop() {
        return false;
    };
    public boolean isQueen() {
        return false;
    };
    public boolean isKing() {
        return false;
    };

}
