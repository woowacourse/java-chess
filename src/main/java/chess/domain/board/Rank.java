package chess.domain.board;

import chess.domain.piece.Direction;

import java.util.Arrays;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int y;

    Rank(int y) {
        this.y = y;
    }

    public static Rank findRankBySignature(String rankInput) {
        return findRankBySignature(Integer.parseInt(rankInput));
    }

    private static Rank findRankBySignature(int y) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.hasSameY(y))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 Rank(열)값 입니다."));
    }

    private boolean hasSameY(int y) {
        return this.y == y;
    }

    public Rank move(Direction direction) {
        int nextY = direction.calculateRank(y);
        return findRankBySignature(nextY);
    }

    public int calculateDifference(Rank rank) {
        return this.y - rank.y;
    }
}
