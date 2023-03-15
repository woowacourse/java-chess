package chess.domain;

public enum RankCoordinate {
    EIGHT,
    SEVEN,
    SIX,
    FIVE,
    FOUR,
    THREE,
    TWO,
    ONE,
    ;

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
