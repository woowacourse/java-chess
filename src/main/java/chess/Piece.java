package chess;

public abstract class Piece {
    private final Camp camp;

    protected Piece(Camp camp) {
        this.camp = camp;
    }

    public final boolean isBlack(){
        return this.camp == Camp.BLACK;
    }
}
