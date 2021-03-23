package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.position.MovePosition;

public class King extends AbstractPiece {
    
    public static final King BLACK_INSTANCE = new King(Color.BLACK);
    public static final King WHITE_INSTANCE = new King(Color.WHITE);
    
    private static final DirectionGroup DIRECTION_GROUP;
    
    static {
        final int MOVABLE_LENGTH = 1;
        DIRECTION_GROUP = new DirectionGroup(Direction.everyDirection(), MOVABLE_LENGTH);
    }
    
    private static final String SYMBOL = "k";
    private static final double SCORE = 0;
    
    private King(Color color) {
        super(color, DIRECTION_GROUP);
    }
    
    public static King from(Color color) {
        if (color.isBlack()) {
            return BLACK_INSTANCE;
        }
        
        return WHITE_INSTANCE;
    }
    
    @Override
    public void checkToMoveToTargetPosition(MovePosition movePosition, Board board) {
        Direction direction = DIRECTION_GROUP.findDirection(movePosition);
        checkObstacleExistsAtDirection(movePosition, direction, board);
    }
    
    @Override
    public boolean isPawn() {
        return false;
    }
    
    @Override
    public boolean isKing() {
        return true;
    }
    
    @Override
    public String getSymbol() {
        return changeColorSymbol(SYMBOL);
    }
    
    @Override
    public double getScore() {
        return SCORE;
    }
}
