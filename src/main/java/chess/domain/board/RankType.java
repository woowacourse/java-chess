package chess.domain.board;

public enum RankType {
    SIDE_RANK,
    PAWN_RANK,
    EMPTY_RANK,
    ;

    public boolean isSideRank() {
        return this == SIDE_RANK;
    }

    public boolean isPawnRank() {
        return this == PAWN_RANK;
    }

    public boolean isEmptyRank() {
        return this == EMPTY_RANK;
    }
}
