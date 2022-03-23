package domain.piece;

import domain.Direction;
import domain.Player;
import domain.piece.Piece;
import domain.piece.PieceSymbol;
import domain.position.Column;
import domain.position.Position;
import domain.position.Row;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    private List<Position> positions = new ArrayList<>();

    public Queen(final Player player) {
        super(player, PieceSymbol.Queen, new QueenDirections());
    }


    @Override
    public List<Position> availableMovePositions(Position source) {
        positions = new ArrayList<>();
        for (Direction direction : directions) {
            calculateAvailablePosition(source, direction);
        }
        return positions;
    }

    private void calculateAvailablePosition(final Position source, final Direction direction) {
        int row = source.getRow() + direction.getRow();
        int column = source.getColumn() + direction.getColumn();

        while (checkOverRange(row, column)) {
            positions.add(new Position(Row.of(row), Column.of(column)));
            row += direction.getRow();
            column += direction.getColumn();
        }
    }

    private boolean checkOverRange(final int row, final int column) {
        return Row.isRowRange(row) && Column.isColumnRange(column);
    }
}
