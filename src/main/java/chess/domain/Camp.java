package chess.domain;

public enum Camp {

    BLACK(8),
    WHITE(1);

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
        return startingRank + 1;
    }

    public Camp transfer() {
        if (this == Camp.WHITE) {
            return Camp.BLACK;
        }
        return Camp.WHITE;
    }

}
