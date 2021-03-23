package chess.domain.position;

import chess.domain.Color;
import chess.domain.piece.Direction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static chess.ChessConstant.DEFAULT_INDEX_OF_BLACK_PAWN;
import static chess.ChessConstant.DEFAULT_INDEX_OF_WHITE_PAWN;

public class MovePosition {
    
    private static final String BLANK = " ";
    private static final int POSITIONS_SIZE = 3;
    
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;
    
    private static final String ERROR_MISMATCH_POSITION_SIZE = "입력받은 위치가 2개가 아닙니다.";
    
    private final Position sourcePosition;
    private final Position targetPosition;
    
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
