package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.position.MovePosition;

public class Queen extends AbstractPiece {
    
    private static final int MOVABLE_LENGTH = 7;
    private static final DirectionGroup DIRECTION_GROUP
            = new DirectionGroup(Direction.everyDirection(), MOVABLE_LENGTH);
    
    private static final String SYMBOL = "q";
    private static final double SCORE = 9;
    
    public Queen(Color color) {
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
