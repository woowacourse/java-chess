package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.moverules.Direction;
import chess.domain.position.Position;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Pawn extends Piece {
    private final static int AVAILABLE_ROW_MOVE_DIFF = 1;
    private final static int INIT_AVAILABLE_COLUMN_DIFF = 1;
    private final static int AVAILABLE_COLUMN_DIFF = 2;
    private final static PieceName pieceName = PieceName.valueOf("PAWN");
    private final static List<Direction> attackDirections = new ArrayList<>();

    private final Position initPosition;

    private Direction forwardDirection;

    public Pawn(Player player, Position position) {
        super(player, pieceName);
        this.initPosition = position;

        if (player.equals(Player.BLACK)) {
            attackDirections.addAll(Arrays.asList(Direction.DIAGONAL_DOWN_LEFT, Direction.DIAGONAL_DOWN_RIGHT));
            forwardDirection = Direction.DOWN;
            directions.add(forwardDirection);
            directions.addAll(attackDirections);
        }

        if (player.equals(Player.WHITE)) {
            attackDirections.addAll(Arrays.asList(Direction.DIAGONAL_TOP_LEFT, Direction.DIAGONAL_TOP_RIGHT));
            forwardDirection = Direction.TOP;
            directions.add(forwardDirection);
            directions.addAll(attackDirections);
        }
    }

    @Override
    public boolean movable(Position from, Position to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        return hasDirection(Direction.getDirection(from, to))
                && validateMovableTileSize(from, to);
    }

    // 상수에 대한 이름 수정 리팩토링
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

    // (예외 상황) 대각선 공격 (1) 대각선이여야 하고, (2) 같은 편이 아니여야 한다.
    public boolean validateAttack(Square target, Direction direction) {
        if (attackDirections.contains(direction)) {
            return !isSamePlayer(target);
        }
        return true;
    }

    // (예외 상황) 전진일 때 empty여야 한다.
    public boolean validateMoveForward(Square target, Direction direction) {
        if (direction == forwardDirection) {
            return target.getClass() == Empty.class;
        }
        return true;
    }
}
