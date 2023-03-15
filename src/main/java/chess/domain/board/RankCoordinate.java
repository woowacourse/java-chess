package chess.domain.board;

public enum RankCoordinate {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1),
    ;

    private final int rowNumber;

    RankCoordinate(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public boolean isWhiteRank() {
        return this == RankCoordinate.ONE || this == RankCoordinate.TWO;
    }

    public boolean isBlackRank() {
        return this == RankCoordinate.SEVEN || this == RankCoordinate.EIGHT;
    }

    public boolean isSideRank() {
        return this == RankCoordinate.ONE || this == RankCoordinate.EIGHT;
    }

    public boolean isPawnRank() {
        return this == RankCoordinate.TWO || this == RankCoordinate.SEVEN;
    }
}
