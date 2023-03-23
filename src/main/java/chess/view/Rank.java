package chess.view;

import java.util.Arrays;

public enum Rank {

    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8);

    private static final String WRONG_RANK_ERROR_MESSAGE = "잘못된 rank 입니다.";

    private final String userIndex;
    private final int coordinateIndex;

    Rank(final String userIndex, final int coordinateIndex) {
        this.userIndex = userIndex;
        this.coordinateIndex = coordinateIndex;
    }

    public static int findByUserIndex(String searchIndex) {
        Rank foundRank = Arrays.stream(values())
                .filter(rank -> rank.userIndex.equalsIgnoreCase(searchIndex))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(WRONG_RANK_ERROR_MESSAGE));
        return foundRank.coordinateIndex;
    }

}
