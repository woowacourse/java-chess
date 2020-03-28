package chess.domain.chesspieces;

import chess.Exceptions.NotMoveException;
import chess.domain.Player;
import chess.domain.direction.Direction;
import chess.domain.position.Position;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {
    private static final int AVAILABLE_ROW_MOVE_DIFF = 1;
    private static final int INIT_AVAILABLE_COLUMN_DIFF = 1;
    private static final int AVAILABLE_COLUMN_DIFF = 2;
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
    public boolean validateMovableTileSize(Position from, Position to) {
        int rowDiff = Row.getDiff(from.getRow(), to.getRow());
        int columnDiff = Column.getDiff(from.getColumn(), to.getColumn());
        int availableColumnDiff = INIT_AVAILABLE_COLUMN_DIFF;
        if (initPosition == from) {
            availableColumnDiff = AVAILABLE_COLUMN_DIFF;
        }
        return Math.abs(rowDiff) <= AVAILABLE_ROW_MOVE_DIFF && Math.abs(columnDiff) <= availableColumnDiff;
    }

    public boolean validateAttack(Square target, Direction direction) {
        if (target.getClass() == Empty.class) {
            return false;
        }

        if (attackDirections.contains(direction)) {
            return !isSamePlayer(target);
        }
        throw new NotMoveException("잘못된 이동입니다.");
    }

    public boolean validateMoveForward(Square target, Direction direction) {
        return direction == forwardDirection && target.getClass() == Empty.class;
    }
}
