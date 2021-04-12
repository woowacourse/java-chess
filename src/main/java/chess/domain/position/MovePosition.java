package chess.domain.position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import chess.domain.chess.Color;
import chess.domain.piece.Direction;

public class MovePosition {

    private static final int SOURCE_POSITION_INDEX = 0;
    private static final int TARGET_POSITION_INDEX = 1;

    private static final String BLANK = " ";
    private static final int TWO_STEP = 2;
    private static final int POSITIONS_SIZE = 2;

    private static final int DEFAULT_INDEX_OF_WHITE_PAWN = 1;
    private static final int DEFAULT_INDEX_OF_BLACK_PAWN = 6;

    private static final String ERROR_MISMATCH_POSITION_SIZE = "입력받은 위치가 2개가 아닙니다.";

    private final Position sourcePosition;
    private final Position targetPosition;

    public MovePosition(String source, String target) {
        this(Position.from(source), Position.from(target));
    }

    public MovePosition(Position sourcePosition, Position targetPosition) {
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    public static MovePosition from(String[] input) {
        final List<String> positions = trimAndExceptBlank(input);
        if (isSizeMismatch(positions)) {
            throw new IllegalArgumentException(ERROR_MISMATCH_POSITION_SIZE);
        }

        final Position sourcePosition = Position.from(positions.get(SOURCE_POSITION_INDEX));
        final Position targetPosition = Position.from(positions.get(TARGET_POSITION_INDEX));
        return new MovePosition(sourcePosition, targetPosition);
    }

    private static List<String> trimAndExceptBlank(String[] input) {
        return Arrays.stream(input)
                     .filter(position -> !isBlank(position))
                     .map(String::trim)
                     .collect(Collectors.toList());
    }

    private static boolean isSizeMismatch(List<String> positions) {
        return positions.size() != POSITIONS_SIZE;
    }

    private static boolean isBlank(String input) {
        return input.isEmpty() || input.equals(BLANK);
    }

    public Position getSourcePosition() {
        return sourcePosition;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }

    public boolean canMove(Direction direction, int movableLength) {
        int xDistance = targetPosition.getX() - sourcePosition.getX();
        int yDistance = targetPosition.getY() - sourcePosition.getY();
        return direction.isCorrectDirection(xDistance, yDistance, movableLength);
    }

    public boolean isArrived(Position sourcePosition) {
        return sourcePosition.equals(targetPosition);
    }

    public boolean isTwoStep() {
        return Math.abs(sourcePosition.getY() - targetPosition.getY()) == TWO_STEP;
    }

    public void checkPawnExistAtDefaultPosition(Color color) {
        if (!isAtDefaultPawnPosition(color)) {
            throw new IllegalArgumentException("폰은 첫 이동 시에만 2칸 움직일 수 있습니다.");
        }
    }

    private boolean isAtDefaultPawnPosition(Color color) {
        if (color.isWhite()) {
            return sourcePosition.existsAtRankOf(DEFAULT_INDEX_OF_WHITE_PAWN);
        }

        return sourcePosition.existsAtRankOf(DEFAULT_INDEX_OF_BLACK_PAWN);
    }
}
