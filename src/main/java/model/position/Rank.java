package model.position;

import model.direction.Direction;

import java.util.Arrays;

public enum Rank {
    ONE('1', 1),
    TWO('2', 2),
    THREE('3', 3),
    FOUR('4', 4),
    FIVE('5', 5),
    SIX('6', 6),
    SEVEN('7', 7),
    EIGHT('8', 8);
    private final char expression;
    private final int index;

    Rank(char expression, int index) {
        this.expression = expression;
        this.index = index;
    }

    public static Rank from(char expression) {
        return Arrays.stream(values())
                     .filter(rank -> rank.expression == expression)
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("일치하는 Rank가 존재하지 않습니다."));
    }

    public boolean canMoveTo(Direction direction) {
        int movedIndex = index + direction.rankDifferential();
        return ONE.index <= movedIndex && movedIndex <= EIGHT.index;
    }

    public Rank moving(Direction direction) {
        return Arrays.stream(values())
                     .filter(rank -> rank.index == index + direction.rankDifferential())
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("현재 Rank에서 해당 방향으로 이동할 수 없습니다."));
    }

    public int index() {
        return index;
    }
}
