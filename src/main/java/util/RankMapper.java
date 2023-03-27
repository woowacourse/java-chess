package util;

import domain.game.Rank;
import java.util.Arrays;


public enum RankMapper {
    ONE("1", Rank.ONE),
    TWO("2", Rank.TWO),
    THREE("3", Rank.THREE),
    FOUR("4", Rank.FOUR),
    FIVE("5", Rank.FIVE),
    SIX("6", Rank.SIX),
    SEVEN("7", Rank.SEVEN),
    EIGHT("8", Rank.EIGHT);

    private final String text;
    private final Rank rank;

    RankMapper(String text, Rank rank) {
        this.text = text;
        this.rank = rank;
    }

    public static Rank convertTextToRank(String text) {
        return Arrays.stream(RankMapper.values())
                .filter(rankMapper -> rankMapper.text.equals(text))
                .map(RankMapper::getRank)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스판의 가로 좌표는 a~h의 범위를 가집니다. 범위 안의 문자를 입력해주세요."));
    }

    public static String convertRankToText(Rank rank) {
        return Arrays.stream(RankMapper.values())
                .filter(rankMapper -> rankMapper.rank.equals(rank))
                .map(RankMapper::getText)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("서버 내부 에서 - 존재하지 않는 Rank를 텍스트로 변환 시도했습니다."));
    }

    private Rank getRank() {
        return this.rank;
    }

    public String getText() {
        return this.text;
    }
}
