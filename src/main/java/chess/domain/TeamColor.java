package chess.domain;

public enum TeamColor {

    BLACK(8),
    WHITE(1);

    private final int startingRank;

    TeamColor(final int startingRank) {
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
}
