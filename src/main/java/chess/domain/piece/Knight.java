package chess.domain.piece;

import java.util.List;

public class Knight extends AbstractPiece {
    
    private static final String SYMBOL = "n";
    private static final int ABLE_LENGTH = 1;
    
    public static final double SCORE = 2.5;
    
    public Knight(Color color, Position position) {
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
        final List<Direction> directions = Direction.knightDirection();
        if (!isMovable(position, directions, ABLE_LENGTH)) {
            throw new IllegalArgumentException("나이트가 이동할 수 없는 위치입니다.");
        }
        return new Knight(color, position);
    }
}
