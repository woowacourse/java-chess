package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.position.MovePosition;

public class Rook extends AbstractPiece {
    private static final int MAX_MOVABLE_LENGTH = 7;
    private static final DirectionGroup DIRECTION_GROUP = new DirectionGroup(Direction.linearDirection(),
            MAX_MOVABLE_LENGTH);
    
    private static final String SYMBOL = "r";
    
    private static final double SCORE = 5;
    
    public Rook(Color color) {
        super(color, DIRECTION_GROUP);
    }
    
    @Override
    public String getSymbol() {
        return changeColorSymbol(SYMBOL);
    }
    
    @Override
    public double getScore() {
        return SCORE;
    }
    
    @Override
    public void checkToMoveToTargetPosition(MovePosition movePosition, Board board) {
        Direction direction = DIRECTION_GROUP.findDirection(movePosition);
        checkObstacleExistsAtDirection(movePosition, direction, board);
    }
}
