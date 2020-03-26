package chess.board;

public class MoveInfo {
    private final Vector vector;
    private final Rank rank;

    public MoveInfo(final Vector vector, final Rank rank) {
        this.vector = vector;
        this.rank = rank;
    }

    public boolean isRangeUnderAbsolute(final int value) {
        return vector.isRangeUnderAbsolute(value);
    }

    public int getRankVariation() {
        return vector.getRankVariation();
    }

    public boolean isStraight() {
        return vector.isStraight();
    }

    public boolean isDiagonal() {
        return vector.isDiagonal();
    }

    public int sumOfAbsolute() {
        return vector.sumOfAbsolute();
    }

    public int subtractOfAbsolute() {
        return vector.subtractOfAbsolute();
    }

    public boolean isSameRank(Rank rank) {
        return this.rank == rank;
    }
}
