package chess.domain.piece;

import static chess.domain.piece.Direction.N;
import static chess.domain.piece.Direction.NE;
import static chess.domain.piece.Direction.NW;
import static chess.domain.piece.Direction.S;
import static chess.domain.piece.Direction.SE;
import static chess.domain.piece.Direction.SW;

import chess.domain.board.Position;
import chess.domain.board.Row;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private static final String CANNOT_MOVE_DIAGONAL = "폰은 상대 말을 공격할 때만 대각선으로 이동할 수 있습니다.";
    private static final List<Direction> WHITE_POSSIBLE_DIRECTIONS = List.of(N, NE, NW);
    private static final List<Direction> BLACK_POSSIBLE_DIRECTIONS = List.of(S, SE, SW);
    private static final int POSSIBLE_DISTANCE = 1;
    private static final int POSSIBLE_INITIAL_DISTANCE = 2;
    private static final Row WHITE_INITIAL_ROW = Row.SECOND;
    private static final Row BLACK_INITIAL_ROW = Row.SEVENTH;

    public Pawn(Color color) {
        super(PieceType.PAWN, color);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public List<Position> calculatePathToValidate(Position current, Position target,
                                                  Piece targetPiece) {
        Direction direction = findValidDirection(current, target);
        if (direction.isDiagonal()) {
            return calculateDiagonalPath(targetPiece);
        }
        return calculateStraightPath(current, target, direction);
    }

    @Override
    public Direction findValidDirection(Position current, Position target) {
        int columnDifference = target.calculateColumnDifference(current);
        int rowDifference = target.calculateRowDifference(current);
        Direction direction = Direction.calculate(columnDifference, rowDifference);
        validateDirection(direction);
        if (isFirstMove(current.getRow())) {
            validateInitialRange(columnDifference, rowDifference);
            return direction;
        }
        validateRange(columnDifference, rowDifference);
        return direction;
    }

    private List<Position> calculateStraightPath(Position current, Position target, Direction direction) {
        List<Position> path = new ArrayList<>();
        Position moved = current.move(direction);
        while (!moved.equals(target)) {
            path.add(moved);
            moved = moved.move(direction);
        }
        path.add(target);
        return path;
    }

    private List<Position> calculateDiagonalPath(Piece targetPiece) {
        if (hasSameColor(targetPiece) || targetPiece.isEmpty()) {
            throw new IllegalArgumentException(CANNOT_MOVE_DIAGONAL);
        }
        return new ArrayList<>();
    }

    private void validateInitialRange(int columnDifference, int rowDifference) {
        if (isValidInitialRange(columnDifference, rowDifference)) {
            throw new IllegalArgumentException(INVALID_POSITION);
        }
    }

    private boolean isValidInitialRange(int columnDifference, int rowDifference) {
        return Math.abs(columnDifference) > POSSIBLE_DISTANCE || Math.abs(rowDifference) > POSSIBLE_INITIAL_DISTANCE;
    }

    @Override
    protected void validateDirection(Direction direction) {
        if (isInvalidDirection(direction)) {
            throw new IllegalArgumentException(INVALID_DIRECTION);
        }
    }

    private boolean isInvalidDirection(Direction direction) {
        if (isSameColor(Color.BLACK)) {
            return isInvalidBlackDirection(direction);
        }
        return isInvalidWhiteDirection(direction);
    }

    private boolean isInvalidBlackDirection(Direction direction) {
        return !BLACK_POSSIBLE_DIRECTIONS.contains(direction);
    }

    private boolean isInvalidWhiteDirection(Direction direction) {
        return !WHITE_POSSIBLE_DIRECTIONS.contains(direction);
    }

    private boolean isFirstMove(Row row) {
        return (row == WHITE_INITIAL_ROW && isSameColor(Color.WHITE))
                || (row == BLACK_INITIAL_ROW && isSameColor(Color.BLACK));
    }

    private void validateRange(int columnDifference, int rowDifference) {
        if (isInvalidRange(columnDifference, rowDifference)) {
            throw new IllegalArgumentException(INVALID_POSITION);
        }
    }

    private boolean isInvalidRange(int columnDifference, int rowDifference) {
        return Math.abs(columnDifference) > POSSIBLE_DISTANCE || Math.abs(rowDifference) > POSSIBLE_DISTANCE;
    }
}
