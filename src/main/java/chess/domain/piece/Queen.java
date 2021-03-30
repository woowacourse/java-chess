package chess.domain.piece;

import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Queen extends Division {

    public static final int QUEEN_SCORE = 9;

    public Queen(Color color) {
        super(color, "q");
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return QUEEN_SCORE;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public List<List<Position>> movablePositions(Position position) {
        List<Direction> directions = Arrays.asList(
                new Direction(1, 0),
                new Direction(0, 1),
                new Direction(-1, 0),
                new Direction(0, -1),
                new Direction(1, 1),
                new Direction(1, -1),
                new Direction(-1, 1),
                new Direction(-1, -1));
        return directions.stream()
                         .map(direction -> position.positionsOfDirection(direction.column(), direction.row()))
                         .collect(Collectors.toList());
    }

    @Override
    public List<List<Position>> killablePositions(Position position) {
        return movablePositions(position);
    }
}
