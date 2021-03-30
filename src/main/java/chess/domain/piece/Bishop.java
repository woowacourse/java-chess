package chess.domain.piece;

import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bishop extends Division {

    public static final int BISHOP_SCORE = 3;

    public Bishop(Color color) {
        super(color, "b");
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return BISHOP_SCORE;
    }

    @Override
    public boolean isPawn() {
        return false;
    }


    @Override
    public List<List<Position>> movablePositions(Position position) {
        List<Direction> directions = Arrays.asList(new Direction(1, 1), new Direction(1, -1), new Direction(-1, 1), new Direction(-1, -1));
        return directions.stream()
                         .map(direction -> position.positionsOfDirection(direction.column(), direction.row()))
                         .collect(Collectors.toList());
    }

    @Override
    public List<List<Position>> killablePositions(Position position) {
        return movablePositions(position);
    }
}
