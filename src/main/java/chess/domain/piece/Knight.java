package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.position.MovePosition;

public class Knight extends AbstractPiece {
    
    private static final int MOVABLE_LENGTH = 1;
    private static final DirectionGroup DIRECTION_GROUP = new DirectionGroup(Direction.knightDirection(),
            MOVABLE_LENGTH);
    
    private static final String SYMBOL = "n";
    public static final double SCORE = 2.5;
    
    public Knight(Color color) {
        super(color, DIRECTION_GROUP);
    }
    
    @Override
    public void checkToMoveToTargetPosition(MovePosition movePosition, Board board) {
        DIRECTION_GROUP.findDirection(movePosition);
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
