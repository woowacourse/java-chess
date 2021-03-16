package chess.domain.piece;

public class Position {
    
    private static final int MIN_INDEX = 0;
    private static final int MAX_INDEX = 7;
    
    private static final String ERROR_OUT_OF_RANGE = "인덱스는 0이상 7이하이어야 합니다.";
    
    private final int position;
    
    private Position(int position) {
        this.position = position;
    }
    
    public static Position from(int position) {
        if (isOutOfBounds(position)) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
        }
        
        return new Position(position);
    }
    
    private static boolean isOutOfBounds(int position) {
        return position < MIN_INDEX || position > MAX_INDEX;
    }
}
