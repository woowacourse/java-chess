package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.position.MovePosition;

public class Pawn extends AbstractPiece {
    
    private static final int MOVABLE_LENGTH = 2;
    public static final Pawn BLACK_INSTANCE
            = new Pawn(Color.BLACK, new DirectionGroup(Direction.blackPawnDirection(), MOVABLE_LENGTH));
    public static final Pawn WHITE_INSTANCE
            = new Pawn(Color.WHITE, new DirectionGroup(Direction.whitePawnDirection(), MOVABLE_LENGTH));
    
    private static final String SYMBOL = "p";
    private static final double SCORE = 1;
    
    private static final String ERROR_PIECE_EXIST_AT_FORWARD_TARGET = "폰이 전진하는 위치에 기물이 있으면 안됩니다.";
    private static final String ERROR_PIECE_DOES_NOT_EXISTS_AT_DIAGONAL_TARGET = "폰은 대각선으로 이동하기 위해서는 상대방의 기물이 있어야 합니다.";
    
    private Pawn(Color color, DirectionGroup directionGroup) {
        super(color, directionGroup);
    }
    
    public static Pawn from(Color color) {
        if (color.isBlack()) {
            return BLACK_INSTANCE;
        }
        
        return WHITE_INSTANCE;
    }
    
    @Override
    public void checkToMoveToTargetPosition(MovePosition movePosition, Board board) {
        Direction direction = directionGroup.findDirection(movePosition);
        checkObstacleExistsAtDirection(movePosition, direction, board);
        
        final boolean isForwardDirection = isForward(direction);
        final boolean isBlankAtTargetPosition = board.isBlank(movePosition.getTargetPosition());
        if (isForwardDirection && !isBlankAtTargetPosition) {
            throw new IllegalArgumentException(ERROR_PIECE_EXIST_AT_FORWARD_TARGET);
        }
        
        if (isForwardDirection && !movePosition.isAtDefaultPawnPosition(color)) {
            throw new IllegalArgumentException("폰은 첫 이동 시에만 2칸 움직일 수 있습니다.");
        }
        
        if (!isForwardDirection && isBlankAtTargetPosition) {
            throw new IllegalArgumentException(ERROR_PIECE_DOES_NOT_EXISTS_AT_DIAGONAL_TARGET);
        }
    }
    
    private boolean isForward(Direction direction) {
        return (direction == Direction.SOUTH) || (direction == Direction.NORTH);
    }
    
    @Override
    public boolean isPawn() {
        return true;
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
