package chess.domain.piece;

import java.util.List;

public class Queen extends AbstractPiece {
    
    private static final String SYMBOL = "q";
    
    private static final double SCORE = 9;
    public static final int ABLE_LENGTH = 7;
    
    public Queen(Color color, Position position) {
        super(color, position);
    }
    
    @Override
    public String getSymbol() {
        return changeColorSymbol(SYMBOL);
    }
    
//    @Override
//    public Queen move(Position position) {
//        final List<Direction> directions = Direction.everyDirection();
//        if (!isMovable(position, directions, ABLE_LENGTH)) {
//            throw new IllegalArgumentException("퀸이 이동할 수 없는 위치입니다.");
//        }
//        return new Queen(color, position);
//    }
    
    @Override
    public Piece move(Position position, List<List<Piece>> board) {
        final List<Direction> directions = Direction.everyDirection();
        Direction direction = findDirection(position, directions, ABLE_LENGTH);
        
        // TODO 장애물 체크
        if (isObstacleAtDirection(position, direction, board)) {
            throw new IllegalArgumentException("이동하는 경로 사이에 기물이 있습니다.");
        }
        return new Queen(color, position);
    }
    
    @Override
    public double getScore() {
        return SCORE;
    }
}
