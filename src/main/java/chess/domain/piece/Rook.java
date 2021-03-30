package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rook extends Division {

    public static final int ROOK_SCORE = 5;

    public Rook(Color color, Position position) {
        super(color, "r", position);
    }

    @Override
    public void moveToEmpty(Position to, Pieces pieces) {
        if (position.isOrthogonal(to)) {
            validateNoneBetween(to, pieces);
            position = to;
        }
    }

    private void validateNoneBetween(Position to, Pieces pieces) {
        List<Position> positions = position.getBetween(to);
        if (positions.stream()
                     .filter(pieces::hasPieceOf)
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
