package chess.domain.piece;

import java.util.List;

public class Bishop extends AbstractPiece {
    
    private static final String SYMBOL = "b";
    private static final int ABLE_LENGTH = 7;
    
    public static final double SCORE = 3;
    
    public Bishop(Color color, Position position) {
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
        final List<Direction> directions = Direction.diagonalDirection();
        if (!isMovable(position, directions, ABLE_LENGTH)) {
            throw new IllegalArgumentException("비숍이 이동할 수 없는 위치입니다.");
        }
        return new Bishop(color, position);
    }
}
