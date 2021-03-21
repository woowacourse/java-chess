package chess.domain.piece;

import chess.game.Board;

public class Blank extends AbstractPiece {
    
    public static final double SCORE = 0;
    private static final DirectionGroup DIRECTION_GROUP = DirectionGroup.createBlankDirectionGroup();
    private static final String SYMBOL = ".";
    
    public Blank() {
        super(Color.BLANK, DIRECTION_GROUP);
    }
    
    public Blank(Color color) {
        super(color, DIRECTION_GROUP);
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
    public Piece move(Position sourcePosition, Position targetPosition, Board board) {
        throw new IllegalArgumentException("해당 위치에는 기물이 존재하지 않습니다");
    }
}
