package chess.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static chess.view.ErrorMessage.NO_RANK_ERROR_GUIDE_MESSAGE;

public enum Rank {

    EIGHT(8, "8"),
    SEVEN(7, "7"),
    SIX(6, "6"),
    FIVE(5, "5"),
    FOUR(4, "4"),
    THREE(3, "3"),
    TWO(2, "2"),
    ONE(1, "1");

    private final int index;
    private final String value;

    Rank(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static List<Rank> getReversedOrderedRanks() {
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
                .orElseThrow(() -> new IllegalArgumentException(NO_RANK_ERROR_GUIDE_MESSAGE.getErrorMessage()));
    }

    public static Rank findRankByValue(String value) {
        return Arrays.stream(values())
                .filter(rank -> rank.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_RANK_ERROR_GUIDE_MESSAGE.getErrorMessage()));
    }

    public int getIndex() {
        return index;
    }
}
