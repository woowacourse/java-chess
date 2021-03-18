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
        if (isForward(direction) && !board.get(position.getX().getPoint()).get(position.getY().getPoint()).isSameColor(Color.BLANK)) {
            throw new IllegalArgumentException("폰은 전진하는 위치에 기물이 있으면 안됩니다.");
        }
        if (!isForward(direction) && board.get(position.getX().getPoint()).get(position.getY().getPoint()).isSameColor(Color.BLANK)) {
            throw new IllegalArgumentException("폰은 대각선으로 이동하기 위해서는 상대방의 기물이 있어야 합니다.");
        }
        // TODO 장애물 체크
        if (isObstacleAtDirection(position, direction, board)) {
            throw new IllegalArgumentException("이동하는 경로 사이에 기물이 있습니다.");
        }
        return new Pawn(color, position);
    }
    
    private boolean isForward(Direction direction) {
        return direction == forward();
    }
    
    private List<Direction> findPawnDirection() {
        if (color.isBlack()) {
            return Direction.blackPawnDirection();
        }
        
        return Direction.whitePawnDirection();
    }
    
    @Override
    protected Direction findDirection(Position position, List<Direction> directions, int ableLength) {
        return directions.stream()
                         .filter(direction -> this.position.canMove(position, direction, ableLength))
                         .findAny()
                         .orElseGet(() -> {
                             if (isFirst() && this.position.canMove(position, forward(),ableLength+1)) {
                                 return forward();
                             }
                             throw new IllegalArgumentException(ERROR_CAN_NOT_MOVE);
                         });
    }
    
    private Direction forward() {
        if (color.isBlack()) {
            return Direction.SOUTH;
        }
        return Direction.NORTH;
    }
    
    private boolean isFirst() {
        if (position.getX().getPoint() == 1 && color.isWhite()) {
            return true;
        }
        if (position.getX().getPoint() == 6 && color.isBlack()) {
            return true;
        }
        return false;
    }
}
