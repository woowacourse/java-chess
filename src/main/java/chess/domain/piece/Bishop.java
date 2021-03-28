package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.position.MovePosition;

public class Bishop extends AbstractPiece {
    
    public static final Bishop BLACK_INSTANCE = new Bishop(Color.BLACK);
    public static final Bishop WHITE_INSTANCE = new Bishop(Color.WHITE);
    
    private static final DirectionGroup DIRECTION_GROUP;
    private static final String SYMBOL = "b";
    private static final double SCORE = 3;

    static {
        final int MOVABLE_LENGTH = 7;
        DIRECTION_GROUP = new DirectionGroup(Direction.diagonalDirection(), MOVABLE_LENGTH);
    }
    
    private Bishop(Color color) {
        super(color, DIRECTION_GROUP);
    }
    
    public static Bishop from(Color color) {
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
        return false;
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
