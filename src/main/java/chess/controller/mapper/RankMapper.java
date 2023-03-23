package chess.controller.mapper;

import java.util.Map;
import java.util.Objects;

import chess.domain.position.Rank;

public class RankMapper {

    private static final Map<String, Rank> RANK_MAP = Map.ofEntries(
            Map.entry("1", Rank.ONE),
            Map.entry("2", Rank.TWO),
            Map.entry("3", Rank.THREE),
            Map.entry("4", Rank.FOUR),
            Map.entry("5", Rank.FIVE),
            Map.entry("6", Rank.SIX),
            Map.entry("7", Rank.SEVEN),
            Map.entry("8", Rank.EIGHT)
    );

    public static Rank map(String input) {
        Rank rank = RANK_MAP.get(input);
        if (Objects.isNull(rank)) {
            throw new IllegalArgumentException("잘못된 랭크입니다");
        }
        return rank;
    }
}
