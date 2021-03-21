package chess.domain.piece;

import chess.game.Board;

public class Pawn extends AbstractPiece {
    
    public static final double SCORE = 1;
    private static final int MAX_MOVABLE_LENGTH = 1;
    private static final String SYMBOL = "p";
    
    public Pawn(Color color, DirectionGroup directionGroup) {
        super(color, directionGroup);
    }
    
    public static Pawn createWhitePawn() {
        final DirectionGroup directionGroup = new DirectionGroup(Direction.whitePawnDirection(), MAX_MOVABLE_LENGTH);
        return new Pawn(Color.WHITE, directionGroup);
    }
    
    public static Pawn createBlackPawn() {
        final DirectionGroup directionGroup = new DirectionGroup(Direction.blackPawnDirection(), MAX_MOVABLE_LENGTH);
        return new Pawn(Color.WHITE, directionGroup);
    }
    
    public static Pawn from(Color color) {
        final DirectionGroup directionGroup = new DirectionGroup(color.getPawnDirections(), MAX_MOVABLE_LENGTH);
        return new Pawn(color, directionGroup);
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
        Direction direction = directionGroup.findDirectionOfPawn(sourcePosition, targetPosition, color);
        if (isObstacleAtDirection(sourcePosition, targetPosition, direction, board)) {
            throw new IllegalArgumentException("이동하는 경로 사이에 기물이 있습니다.");
        }
        
        final boolean isForwardDirection = isForward(direction);
        final boolean isBlankAtTargetPosition = board.isBlank(targetPosition);
        if (isForwardDirection && !isBlankAtTargetPosition) {
            throw new IllegalArgumentException("폰이 전진하는 위치에 기물이 있으면 안됩니다.");
        }
        
        if (!isForwardDirection && isBlankAtTargetPosition) {
            throw new IllegalArgumentException("폰은 대각선으로 이동하기 위해서는 상대방의 기물이 있어야 합니다.");
        }
        
        return Pawn.from(color);
    }
    
    private boolean isForward(Direction direction) {
        return (direction == Direction.SOUTH) || (direction == Direction.NORTH);
    }
}
