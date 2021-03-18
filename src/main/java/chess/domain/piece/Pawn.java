package chess.domain.piece;

import java.util.List;

public class Pawn extends AbstractPiece {
    
    private static final String SYMBOL = "p";
    
    public static final double SCORE = 1;
    private static final int ABLE_LENGTH = 1;
    
    public Pawn(Color color, Position position) {
        super(color, position);
    }
    
    @Override
    public String getSymbol() {
        return changeColorSymbol(SYMBOL);
    }
    
    // TODO 1 또는 0.5 가 가능하다
    @Override
    public double getScore() {
        return SCORE;
    }
    
    // TODO 대각선으로 이동할 대는 대각선에 상대방 기물이 있어야 함
    // TODO 앞으로 전진할 때는 첫 이동일 때만 2칸 이동 가능
    // TODO 앞에 상대방 기물이 있을 때 전진 불가
//    @Override
//    public Piece move(Position position) {
//        final List<Direction> directions = findPawnDirection();
//        if (!isMovable(position, directions, ABLE_LENGTH)) {
//            throw new IllegalArgumentException("폰이 이동할 수 없는 위치입니다.");
//        }
//        return new Pawn(color, position);
//    }
    
    @Override
    public Piece move(Position position, List<List<Piece>> board) {
        final List<Direction> directions = findPawnDirection();
        Direction direction = findDirection(position, directions, ABLE_LENGTH);
        
        // TODO 장애물 체크
        if (isObstacleAtDirection(position, direction, board)) {
            throw new IllegalArgumentException("이동하는 경로 사이에 기물이 있습니다.");
        }
        return new Pawn(color, position);
    }
    
    private List<Direction> findPawnDirection() {
        if (color.isBlack()) {
            return Direction.blackPawnDirection();
        }
        
        return Direction.whitePawnDirection();
    }
}
