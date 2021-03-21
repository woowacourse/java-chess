package chess.domain.piece;

import chess.game.Board;

public class Knight extends AbstractPiece {
    
    public static final double SCORE = 2.5;
    private static final int MAX_MOVABLE_LENGTH = 1;
    private static final DirectionGroup DIRECTION_GROUP = new DirectionGroup(Direction.knightDirection(),
            MAX_MOVABLE_LENGTH);
    private static final String SYMBOL = "n";
    
    public Knight(Color color) {
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
    public Piece move(Position sourcePosition, Position targetPosition, Board board) {
        Direction direction = DIRECTION_GROUP.findDirection(sourcePosition, targetPosition);
        
        if (!board.isBlank(targetPosition)) {
            throw new IllegalArgumentException("타겟 위치에 이미 기물이 있습니다.");
        }
        
        return new Knight(color);
    }
}
