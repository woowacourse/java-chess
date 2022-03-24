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

    public SpecificLocationPiece(Player player, PieceSymbol pieceSymbol,
        DirectionsGenerator directionsGenerator) {
        super(player, pieceSymbol, directionsGenerator);
    }

    @Override
    List<Position> calculateAvailablePosition(Position source, Direction direction) {
        List<Position> positions = new ArrayList<>();
        int row = source.getRow() + direction.getRow();
        int column = source.getColumn() + direction.getColumn();

        if (checkOverRange(row, column)) {
            positions.add(new Position(Row.of(row), Column.of(column)));
        }
        return positions;
    }
}
