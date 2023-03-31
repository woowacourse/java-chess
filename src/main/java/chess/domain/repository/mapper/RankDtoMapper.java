package chess.domain.repository.mapper;

import chess.domain.position.Rank;
import java.util.Arrays;

public enum RankDtoMapper {
    EIGHT(Rank.EIGHT, "8"),
    SEVEN(Rank.SEVEN, "7"),
    SIX(Rank.SIX, "6"),
    FIVE(Rank.FIVE, "5"),
    FOUR(Rank.FOUR, "4"),
    THREE(Rank.THREE, "3"),
    TWO(Rank.TWO, "2"),
    ONE(Rank.ONE, "1"),
    ;

    private final Rank rank;
    private final String value;

    RankDtoMapper(Rank rank, String value) {
        this.rank = rank;
        this.value = value;
    }

    public static String convertToRankValue(Rank rank) {
        return Arrays.stream(RankDtoMapper.values())
                .filter(it -> it.rank == rank)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 랭크 입니다."))
                .value;
    }

    public static Rank convertToRank(String rankValue) {
        return Arrays.stream(RankDtoMapper.values())
                .filter(it -> it.value.equals(rankValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 랭크 입니다."))
                .rank;
    }
}
