package domain.piece;

import domain.Direction;
import domain.Player;
import domain.position.Column;
import domain.position.Position;
import domain.position.Row;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private static final List<Direction> directions;
    private List<Position> positions = new ArrayList<>();

    public King(final Player player) {
        super(player, PieceSymbol.King);
    }

    static {
        directions = new ArrayList<>();
        directions.add(Direction.NORTHEAST);
        directions.add(Direction.NORTHWEST);
        directions.add(Direction.SOUTHEAST);
        directions.add(Direction.SOUTHWEST);
        directions.add(Direction.EAST);
        directions.add(Direction.WEST);
        directions.add(Direction.SOUTH);
        directions.add(Direction.NORTH);
    }

    @Override
    public List<Position> availableMovePositions(Position source) {
        positions = new ArrayList<>();
        for (Direction direction : directions) {
            calculateAvailablePosition(source, direction);
        }
        return positions;
    }

    private void calculateAvailablePosition(Position source, Direction direction) {
        int row = source.getRow() + direction.getRow();
        int column = source.getColumn() + direction.getColumn();

        positions.add(new Position(Row.of(row), Column.of(column)));
    }
}
