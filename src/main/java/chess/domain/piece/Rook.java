package chess.domain.piece;

import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rook extends Division {

    public static final int ROOK_SCORE = 5;

    public Rook(Color color) {
        super(color, "r");
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return ROOK_SCORE;
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
                new Direction(0, -1));
        return directions.stream()
                         .map(direction -> position.positionsOfDirection(direction.column(), direction.row()))
                         .collect(Collectors.toList());
    }

    @Override
    public List<List<Position>> killablePositions(Position position) {
        return null;
    }
}
