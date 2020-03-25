package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.moverules.Direction;
import chess.domain.position.Position;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.Arrays;
import java.util.Objects;

public class Knight extends Piece {
    private static PieceName pieceName = PieceName.valueOf("KNIGHT");

    public Knight(Player player) {
        super(player, pieceName);
        directions.addAll(Arrays.asList(Direction.values()));
    }

    @Override
    public boolean movable(Position source, Position target) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        int rowDiff = Row.getDiff(source.getRow(), target.getRow());
        int columnDiff = Column.getDiff(source.getColumn(), target.getColumn());
        if (!hasMoveRule(Direction.getMoveRule(source, target))
        && !validateMovePosition(source, target)) {
            return false;
        }
        return validateMovableTileSize(rowDiff, columnDiff);
    }

    public boolean validateMovePosition(Position source, Position target) {
        return Arrays.stream(MovePosition.values())
                .anyMatch(movePosition -> movePosition.contains(source, target));
    }

    @Override
    public boolean validateMovableTileSize(int rowDiff, int columnDiff) {
        return false;
    }

    private enum MovePosition {
        NORTH_EAST(1, 2),
        NORTH_WEST(-1, 2),
        EAST_NORTH(2, 1),
        EAST_SOUTH(2, -1),
        SOUTH_EAST(1, -2),
        SOUTH_WEST(-1, -2),
        WEST_SOUTH(-2, -1),
        WEST_NORTH(-2, 1);

        private int rowDiff;
        private int columnDiff;

        MovePosition(int rowDiff, int columnDiff) {
            this.rowDiff = rowDiff;
            this.columnDiff = columnDiff;
        }

        boolean contains(Position source, Position target) {
            return (source.getRow().ordinal() + rowDiff == target.getRow().ordinal())
                    && (source.getColumn().ordinal() + columnDiff == target.getColumn().ordinal());
        }
    }
}
