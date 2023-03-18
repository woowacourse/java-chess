package chess.domain.board;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    EIGHT(8, "8"),
    SEVEN(7, "7"),
    SIX(6, "6"),
    FIVE(5, "5"),
    FOUR(4, "4"),
    THREE(3, "3"),
    TWO(2, "2"),
    ONE(1, "1");

    private static final String NO_RANK_ERROR_GUIDE_MESSAGE = "존재하지 않는 Rank입니다";
    private final int index;
    private final String value;

    Rank(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static List<Rank> getOrderedRanks() {
        return Arrays.stream(values()).sorted(new Comparator<Rank>() {

                    @Override
                    public int compare(Rank o1, Rank o2) {
                        return o2.index - o1.index;
                    }
                })
                .collect(Collectors.toList());
    }

    public static Rank findRankByIndex(int index) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_RANK_ERROR_GUIDE_MESSAGE));
    }

    public static Rank findRankByValue(String value) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_RANK_ERROR_GUIDE_MESSAGE));
    }

    public int getIndex() {
        return index;
    }
}
