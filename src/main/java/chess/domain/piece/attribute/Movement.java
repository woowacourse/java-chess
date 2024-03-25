package chess.domain.piece.attribute;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import chess.domain.chessboard.attribute.Direction;

public class Movement {

    private final Queue<Direction> directions;

    private Movement(final Queue<Direction> directions) {
        this.directions = directions;
    }

    public static Movement of(final Direction... directions) {
        if (directions == null || directions.length == 0) {
            throw new IllegalArgumentException("이동을 위한 방향은 1개 이상입니다.");
        }
        return new Movement(new LinkedList<>(Arrays.asList(directions)));
    }

    public List<Direction> directions() {
        return directions.stream()
                .toList();
    }
}
