package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.direction.Direction;
import chess.domain.position.Position;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Pawn extends Piece {
    private static final int MOVABLE_ROW_MOVE_DIFF = 1;
    private static final int INIT_MOVABLE_COLUMN_DIFF = 1;
    private static final int MOVABLE_COLUMN_DIFF = 2;
    private static final String PAWN_NAME = "PAWN";
    private static final PieceInfo PIECE_INFO = PieceInfo.valueOf(PAWN_NAME);

    private final List<Direction> attackDirections = new ArrayList<>();

    private final Position initPosition;

    private Direction forwardDirection;

    public Pawn(Player player, Position position) {
        super(player, PIECE_INFO);
        this.initPosition = position;

        if (player.equals(Player.BLACK)) {
            forwardDirection = Direction.DOWN;
            attackDirections.addAll(Arrays.asList(Direction.DIAGONAL_DOWN_LEFT, Direction.DIAGONAL_DOWN_RIGHT));
            directions.add(forwardDirection);
            directions.addAll(attackDirections);
        }

        if (player.equals(Player.WHITE)) {
            forwardDirection = Direction.TOP;
            attackDirections.addAll(Arrays.asList(Direction.DIAGONAL_TOP_LEFT, Direction.DIAGONAL_TOP_RIGHT));
            directions.add(forwardDirection);
            directions.addAll(attackDirections);
        }
    }

    @Override
    public boolean validateTileSize(Position from, Position to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        int rowDiff = Row.getDiff(from.getRow(), to.getRow());
        int columnDiff = Column.getDiff(from.getColumn(), to.getColumn());
        int movableColumnDiff = INIT_MOVABLE_COLUMN_DIFF;
        if (initPosition == from) {
            movableColumnDiff = MOVABLE_COLUMN_DIFF;
        }
        return Math.abs(rowDiff) <= MOVABLE_ROW_MOVE_DIFF && Math.abs(columnDiff) <= movableColumnDiff;
    }

    @Override
    public boolean validateDirection(Direction direction, Piece target) {
        return hasDirection(direction)
                && (validateMovdAttack(direction, target) || validateMoveForward(direction, target));
    };

//    public boolean validate(Direction direction, boolean isSamePlayer, boolean isTargetEmpty) {
//////        return validateMovdAttack(direction, isTargetEmpty, isSamePlayer)
//////                || validateMoveForward(direction, isTargetEmpty);
//////    }

    public boolean validateMovdAttack(Direction direction, Piece target) {
        Objects.requireNonNull(direction);
        Objects.requireNonNull(target);

        return attackDirections.contains(direction)
                && !(target instanceof Empty)
                && !(this.isSamePlayer(target));
    }

    public boolean validateMoveForward(Direction direction, Piece target) {
        Objects.requireNonNull(direction);
        return direction == forwardDirection && target instanceof Empty;
    }
}
