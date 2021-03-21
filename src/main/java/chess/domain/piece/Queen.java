package chess.domain.piece;

import chess.game.Board;

public class Queen extends AbstractPiece {
    
    private static final String SYMBOL = "q";
    
    private static final double SCORE = 9;
    
    private static final int MAX_MOVABLE_LENGTH = 7;
    private static final DirectionGroup DIRECTION_GROUP = new DirectionGroup(Direction.everyDirection(),
            MAX_MOVABLE_LENGTH);
    
    public Queen(Color color) {
        super(color, DIRECTION_GROUP);
    }
    
    @Override
    public String getSymbol() {
        return changeColorSymbol(SYMBOL);
    }
    
    @Override
    public Piece move(Position sourcePosition, Position targetPosition, Board board) {
        Direction direction = DIRECTION_GROUP.findDirection(sourcePosition, targetPosition);
        
        if (isObstacleAtDirection(sourcePosition, targetPosition, direction, board)) {
            throw new IllegalArgumentException("이동하는 경로 사이에 기물이 있습니다.");
        }
        
        if (!board.isBlank(targetPosition)) {
            throw new IllegalArgumentException("타겟 위치에 이미 기물이 있습니다.");
        }
        
        return new Queen(color);
    }
    
    @Override
    public double getScore() {
        return SCORE;
    }
}
