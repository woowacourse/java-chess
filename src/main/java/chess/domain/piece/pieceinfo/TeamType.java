package chess.domain.piece.pieceinfo;

public enum TeamType {
    WHITE(-1),
    BLACK(1),
    NEUTRAL(0);

    private final int type;

    TeamType(final int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
