package chess.domain;

public enum RankType {
    SIDE_RANK,
    PAWN_RANK,
    EMPTY_RANK,
    ;

    public static RankType of(RankCoordinate rankCoordinate) {
        if (rankCoordinate == RankCoordinate.ONE || rankCoordinate == RankCoordinate.EIGHT) {
            return RankType.SIDE_RANK;
        }
        if (rankCoordinate == RankCoordinate.TWO || rankCoordinate == RankCoordinate.SEVEN) {
            return RankType.PAWN_RANK;
        }
        return RankType.EMPTY_RANK;
    }

    public boolean isSideRank() {
        return this == SIDE_RANK;
    }

    public boolean isPawnRank() {
        return this == PAWN_RANK;
    }
}
