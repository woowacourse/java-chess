package chess.domain.piece;

import java.util.List;

public class Blank extends AbstractPiece {
    
    private static final String SYMBOL = ".";
    
    public static final double SCORE = 0;
    
    public Blank(Color color, Position position) {
        super(color, position);
    }
    
    @Override
    public String getSymbol() {
        return SYMBOL;
    }
    
    @Override
    public double getScore() {
        return SCORE;
    }
    
    @Override
    public Piece move(Position position, List<List<Piece>> board) {
        throw new IllegalArgumentException("해당 위치에 말이 존재하지 않습니다");
    }
}
