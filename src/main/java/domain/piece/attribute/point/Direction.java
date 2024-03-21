package domain.piece.attribute.point;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public enum Direction {
    UP(index -> new Index(index.nextVertical(), index.horizontal())),
    UP_RIGHT(index -> new Index(index.nextVertical(), index.nextHorizontal())),
    RIGHT(index -> new Index(index.vertical(), index.nextHorizontal())),
    DOWN_RIGHT(index -> new Index(index.prevVertical(), index.nextHorizontal())),
    DOWN(index -> new Index(index.prevVertical(), index.horizontal())),
    DOWN_LEFT(index -> new Index(index.prevVertical(), index.prevHorizontal())),
    LEFT(index -> new Index(index.vertical(), index.prevHorizontal())),
    UP_LEFT(index -> new Index(index.nextVertical(), index.prevHorizontal()));
    private UnaryOperator<Index> function;

    Direction(final UnaryOperator<Index> function) {
        this.function = function;
    }

    public Index move(Index index) {
        return function.apply(index);
    }

    // TODO : PAWN 에서 사용하기 위함 + 함수명이 Straight 일시 왼쪽 오른쪽 선도 생각 가능
    public boolean isStraight() {
        return this == UP || this == DOWN;
    }

    public static List<Direction> all() {
        return Arrays.stream(values())
                     .toList();
    }
}
