package chess.domain.board;

import chess.exception.RankCanNotFindException;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Rank {
    EIGHT(7),
    SEVEN(6),
    SIX(5),
    FIVE(4),
    FOUR(3),
    THREE(2),
    TWO(1),
    ONE(0);

    public static final char DIFFERENCE_BETWEEN_LETTER_AND_INDEX = '1';
    private static final Map<Integer, Rank> RANKS = Collections.unmodifiableMap(Stream.of(values())
            .collect(Collectors.toMap(Rank::getRank, Function.identity())));

    private final int rank;

    Rank(int rank) {
        this.rank = rank;
    }

    public static Rank findRankByLetter(char letter) {
        return findRank(letter - DIFFERENCE_BETWEEN_LETTER_AND_INDEX);
    }

    public static Rank findRank(int rankIndex) {
        if (!RANKS.containsKey(rankIndex)) {
            throw new RankCanNotFindException();
        }
        return RANKS.get(rankIndex);
    }

    public int calculateGap(Rank other) {
        return rank - other.rank;
    }

    public int getRank() {
        return rank;
    }
}
