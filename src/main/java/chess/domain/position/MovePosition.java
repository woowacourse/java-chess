package chess.domain.position;

import chess.domain.Color;
import chess.domain.piece.Direction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static chess.ChessConstant.DEFAULT_INDEX_OF_BLACK_PAWN;
import static chess.ChessConstant.DEFAULT_INDEX_OF_WHITE_PAWN;

public class MovePosition {
    
    private static final String BLANK = " ";
    
    private static final int POSITIONS_SIZE = 2;
    private static final int SOURCE_POSITION_INDEX = 0;
    private static final int TARGET_POSITION_INDEX = 1;
    
    private static final int MIN_MOVE_LENGTH = 1;
    
    private final Position sourcePosition;
    private final Position targetPosition;
    
    public MovePosition(Position sourcePosition, Position targetPosition) {
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }
    
    public static MovePosition from(String[] input) {
        final List<String> positions = trimAndExceptBlank(input);
        if (isSizeMismatch(positions)) {
            throw new IllegalArgumentException("입력받은 위치가 2개가 아닙니다.");
        }
        
        final Position sourcePosition = Position.of(positions.get(SOURCE_POSITION_INDEX));
        final Position targetPosition = Position.of(positions.get(TARGET_POSITION_INDEX));
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
        return IntStream.rangeClosed(MIN_MOVE_LENGTH, movableLength)
                        .mapToObj(distance -> {
                            final int xDistance = direction.getXDegree() * distance;
                            final int yDistance = direction.getYDegree() * distance;
                            return sourcePosition.add(xDistance, yDistance);
                        })
                        .anyMatch(targetPosition::equals);
    }
    
    public boolean isAtDefaultPawnPosition(Color color) {
        if (color.isWhite()) {
            return sourcePosition.existsAtRankOf(DEFAULT_INDEX_OF_WHITE_PAWN);
        }
        
        return sourcePosition.existsAtRankOf(DEFAULT_INDEX_OF_BLACK_PAWN);
    }
    
    public boolean isArrived(Position sourcePosition) {
        return sourcePosition.equals(targetPosition);
    }
}
