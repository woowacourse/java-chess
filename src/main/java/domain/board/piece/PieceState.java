package domain.board.piece;

public final class PieceState {

    private final PieceType type;
    private final Camp camp;

    public PieceState(final PieceType type, final Camp camp) {
        this.type = type;
        this.camp = camp;
    }

    public PieceType getType() {
        return type;
    }

    public Camp getCamp() {
        return camp;
    }

    @Override
    public String toString() {
        return "PieceState{" +
            "type=" + type +
            ", camp=" + camp +
            '}';
    }
}
