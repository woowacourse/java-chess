package chess.domain;

public enum Camp {

    BLACK(8),
    WHITE(1),
    EMPTY(3);

    private final int startingRank;

    Camp(final int startingRank) {
        this.startingRank = startingRank;
    }


    public int startingRank() {
        return startingRank;
    }

    public int startingPawnRank() {
        if (this == BLACK) {
            return startingRank - 1;
        }
        if (this == WHITE) {
            return startingRank + 1;
        }
        throw new IllegalStateException("EMPTY 상태의 진영입니다.");
    }

    public Camp transfer() {
        if (this == Camp.WHITE) {
            return Camp.BLACK;
        }
        return Camp.WHITE;
    }

}
