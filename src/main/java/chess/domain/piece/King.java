package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.position.MovePosition;

public class King extends AbstractPiece {
    
    private static final int MOVABLE_LENGTH = 1;
    private static final DirectionGroup DIRECTION_GROUP = new DirectionGroup(Direction.everyDirection(),
            MOVABLE_LENGTH);
    
    private static final String SYMBOL = "k";
    public static final double SCORE = 0;
    
    public King(Color color) {
        super(color, DIRECTION_GROUP);
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
