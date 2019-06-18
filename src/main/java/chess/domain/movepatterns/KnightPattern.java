package chess.domain.movepatterns;

import chess.domain.Point;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class KnightPattern implements MovePattern {

    private static final int FIRST_DIRECT_INDEX = 0;
    private static final int LAST_DIRECT_INDEX = 8;

    private List<Integer> directX = Arrays.asList(-2, -2, -1, -1, 1, 1, 2, 2);
    private List<Integer> directY = Arrays.asList(-1, 1, -2, 2, -2, 2, -1, 1);

    @Override
    public boolean canMove(Point source, Point target) {
        return IntStream.range(FIRST_DIRECT_INDEX, LAST_DIRECT_INDEX)
                .anyMatch(i -> {
                    int nextX = source.getX() + directX.get(i);
                    int nextY = source.getY() + directY.get(i);
                    return target.equals(new Point(nextX, nextY));
                });
    }
}
