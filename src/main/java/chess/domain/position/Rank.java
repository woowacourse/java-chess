package chess.domain.position;

import java.util.Arrays;

public enum Rank {

    ONE("1", 0),
    TWO("2", 1),
    THREE("3", 2),
    FOUR("4", 3),
    FIVE("5", 4),
    SIX("6", 5),
    SEVEN("7", 6),
    EIGHT("8", 7);

    public static final int RANKS_TOTAL_SIZE = 8;

    public static final String INVALID_RANK_EXCEPTION_MESSAGE = "유효하지 않은 랭크니다. (랭크는 1~8까지 입력 가능합니다.)";
    public static final String INVALID_RANK_INDEX_EXCEPTION_MESSAGE = "유효하지 않은 랭크 인덱스입니다. (랭크 인덱스는  0~7까지 입력 가능합니다.)";

    private final String rawRank;
    private final int rankIdx;

    Rank(String rank, int rankIdx) {
        this.rawRank = rank;
        this.rankIdx = rankIdx;
    }

    public static Rank from(String value) {
        return Arrays.stream(values())
            .filter(rank -> rank.rawRank.equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK_EXCEPTION_MESSAGE));
    }

    public static Rank of(int valueIdx) {
        return Arrays.stream(values())
            .filter(rank -> rank.rankIdx == valueIdx)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK_INDEX_EXCEPTION_MESSAGE));
    }

    public int rawDifference(Rank targetRank) {
        return targetRank.rankIdx - this.rankIdx;
    }

    public static boolean isMappedRankIdx(String rank, int idx) {
        return from(rank).getRankIdx() == idx;
    }

    public String getRawRank() {
        return rawRank;
    }

    public int getRankIdx() {
        return rankIdx;
    }

    @Override
    public String toString() {
        return "Rank{" +
            "rawRank='" + rawRank + '\'' +
            ", rankIdx=" + rankIdx +
            '}';
    }

}
