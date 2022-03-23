package domain.piece;

import domain.Player;
import domain.directions.Direction;
import domain.directions.DirectionsGenerator;
import domain.position.Column;
import domain.position.Position;
import domain.position.Row;
import java.util.ArrayList;
import java.util.List;

public class SpecificLocationPiece extends Piece {

    private List<Position> positions = new ArrayList<>();

    public SpecificLocationPiece(Player player, PieceSymbol pieceSymbol,
        DirectionsGenerator directionsGenerator) {
        super(player, pieceSymbol, directionsGenerator);
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
