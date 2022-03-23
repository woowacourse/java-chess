package domain.piece;

import domain.directions.Direction;
import domain.directions.DirectionsGenerator;
import domain.Player;
import domain.position.Column;
import domain.position.Position;
import domain.position.Row;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private List<Position> positions = new ArrayList<>();

    public Pawn(final Player player, final DirectionsGenerator directionsGenerator) {
        super(player, PieceSymbol.Pawn, directionsGenerator);
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
