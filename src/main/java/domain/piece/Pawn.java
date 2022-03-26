package domain.piece;

import domain.Player;
import domain.directions.Direction;
import domain.directions.DirectionsGenerator;
import domain.position.Column;
import domain.position.Position;
import domain.position.Row;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends SpecificLocationPiece {

    private int startLine;

    public Pawn(final Player player, final DirectionsGenerator directionsGenerator, int startLine) {
        super(player, PieceSymbol.Pawn, directionsGenerator);
        this.startLine = startLine;
    }


    @Override
    List<Position> calculateAvailablePosition(Position source, Direction direction) {
        // 첫 이동이다
        if (!isFirstMove(source) && isTwoSpaceMoveDirection(direction)) {
            return null;
        }
        List<Position> positions = new ArrayList<>();
        if (isTwoSpaceMoveDirection(direction)) {
            calculatePawnTwoSpaceMove(positions, source, direction);
        }
        int row = source.getRow() + direction.getRow();
        int column = source.getColumn() + direction.getColumn();

        if (checkOverRange(row, column)) {
            positions.add(new Position(Row.of(row), Column.of(column)));
        }
        return positions;
    }

    private boolean isFirstMove(Position source) {
        return source.getRow() == startLine;
    }

    private boolean isTwoSpaceMoveDirection(Direction direction) {
        return direction.equals(Direction.SOUTH_SOUTH) || direction.equals(Direction.NORTH_NORTH);
    }

    private void calculatePawnTwoSpaceMove(List<Position> positions, Position source,
        Direction direction) {
        Direction addDirection = generateAddDirection(direction);
        if (addDirection != null) {
            int row = source.getRow() + addDirection.getRow();
            int column = source.getColumn() + addDirection.getColumn();
            if (checkOverRange(row, column)) {
                positions.add(new Position(Row.of(row), Column.of(column)));
            }
        }
    }

    private Direction generateAddDirection(Direction direction) {
        if (direction.equals(Direction.NORTH_NORTH)) {
            return Direction.NORTH;
        }
        if (direction.equals(Direction.SOUTH_SOUTH)) {
            return Direction.SOUTH;
        }
        return null;
    }
}
