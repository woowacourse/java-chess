package chess.domain.piece;

import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bishop extends Division {

    public static final int BISHOP_SCORE = 3;

    public Bishop(Color color, Position position) {
        super(color, "b", position);
    }

    @Override
    public void moveToEmpty(Position to, Pieces pieces) {
        if (position.isDiagonal(to)) {
            validateNoneBetween(to, pieces);
            position = to;
        }
    }

    private void validateNoneBetween(Position to, Pieces pieces) {
        List<Position> positions = position.getBetween(to);
        if (positions.stream()
                     .anyMatch(pieces::hasPieceOf)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void moveForKill(Position to, Pieces pieces) {
        this.moveToEmpty(to, pieces);
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
