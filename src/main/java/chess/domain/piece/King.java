package chess.domain.piece;

import java.util.List;

public class King extends AbstractPiece {
    
    private static final String SYMBOL = "k";
    private static final int ABLE_LENGTH = 1;
    
    public static final double SCORE = 0;
    
    public King(Color color, Position position) {
        super(color, position);
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
    public Piece move(Position position) {
        final List<Direction> directions = Direction.everyDirection();
        if (!isMovable(position, directions, ABLE_LENGTH)) {
            throw new IllegalArgumentException("퀸이 이동할 수 없는 위치입니다.");
        }
        return new King(color, position);
    }
}
