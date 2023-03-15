package chess.piece;

public abstract class Piece {
    private final Camp camp;

    public Piece(Camp camp) {
        this.camp = camp;
    }
    abstract public PieceType getPieceType();
    public boolean isWhite(){return this.camp == Camp.WHITE;}
}
