package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.position.MovePosition;

public class Blank implements Piece {
    
    public static final Blank INSTANCE = new Blank();
    
    private static final String SYMBOL = ".";
    private static final double SCORE = 0;
    
    private static final String ERROR_SQUARE_IS_BLANK = "선택한 위치는 빈 칸입니다.";
    
    @Override
    public void checkToMoveToTargetPosition(MovePosition movePosition, Board board) {
        throw new UnsupportedOperationException(ERROR_SQUARE_IS_BLANK);
    }
    
    @Override
    public boolean isSameColorAs(Color color) {
        return false;
    }
    
    @Override
    public boolean isBlank() {
        return true;
    }
    
    @Override
    public boolean isPawn() {
        return false;
    }
    
    @Override
    public boolean isKing() {
        return false;
    }
    
    @Override
    public String getSymbol() {
        return SYMBOL;
    }
    
    @Override
    public double getScore() {
        return SCORE;
    }
}
