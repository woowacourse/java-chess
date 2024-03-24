package chess.domain;

import java.util.Map;

public enum Rank {

    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5),
    SIXTH(6),
    SEVENTH(7),
    EIGHTH(8);


    private static final int MIN_RANK = 1;
    private static final int MAX_RANK = 8;
    private static final Map<Integer, Rank> POOL = Map.of(
            1, FIRST, 2, SECOND, 3, THIRD, 4, FOURTH,
            5, FIFTH, 6, SIXTH, 7, SEVENTH, 8, EIGHTH
    );

    private final int rank;

    Rank(int rank) {
        this.rank = rank;
    }

    public static Rank of(int rank) {
        if (!POOL.containsKey(rank)) {
            throw new IllegalArgumentException(String.format("세로 위치는 %d ~ %d 사이의 값이어야 합니다.", MIN_RANK, MAX_RANK));
        }
        return POOL.get(rank);
    }

    public int distance(Rank rank) {
        return this.rank - rank.rank;
    }

    // TODO: 기물 특화 로직 제거

    public boolean isFirstRank() {
        return rank == 2 || rank == 7;
    }
    public Rank add(int directionOfRank) {
        return Rank.of(rank + directionOfRank);
    }

    public boolean addable(int addRank) {
        int addedRank = this.rank + addRank;

        return addedRank >= MIN_RANK && addedRank <= MAX_RANK;
    }

    public static int maxValue() {
        return MAX_RANK;
    }

    public static int minValue() {
        return MIN_RANK;
    }
}
