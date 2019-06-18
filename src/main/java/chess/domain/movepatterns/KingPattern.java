package chess.domain.movepatterns;

import chess.domain.Point;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class KingPattern implements MovePattern {

    private List<Integer> directX = Arrays.asList(-1, -1, -1, 0, 0, 1, 1, 1);
    private List<Integer> directY = Arrays.asList(-1, 0, 1, -1, 1, -1, 0, 1);

    @Override
    public boolean canMove(Point source, Point target) {
        return IntStream.range(0, 8)
                .anyMatch(i -> {
                    int nextX = source.getX() + directX.get(i);
                    int nextY = source.getY() + directY.get(i);
                    return target.equals(new Point(nextX, nextY));
                });
    }
}
