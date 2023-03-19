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

    public int findPawnStartRank() {
        if (this == BLACK) {
            return startingRank - 1;
        }
        return startingRank + 1;
    }

    public TeamColor transfer() {
        if (this == TeamColor.WHITE) {
            return TeamColor.BLACK;
        }
        return TeamColor.WHITE;
    }

}
