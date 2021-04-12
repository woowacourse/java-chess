package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.chess.Color;
import chess.domain.position.MovePosition;

public class Blank extends AbstractPiece {

    public static final Blank INSTANCE;

    static {
        final DirectionGroup DIRECTION_GROUP = DirectionGroup.empty();
        INSTANCE = new Blank(Color.BLANK, DIRECTION_GROUP);
    }

    private static final String SYMBOL = ".";
    private static final String NAME = "BLANK";
    private static final double SCORE = 0;

    private static final String ERROR_SQUARE_IS_BLANK = "선택한 위치는 빈 칸입니다.";

    public Blank(Color color, DirectionGroup DIRECTION_GROUP) {
        super(color, DIRECTION_GROUP);
    }

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
    public String getName() {
        return NAME;
    }

    @Override
    public Color getColor() {
        return Color.BLANK;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
