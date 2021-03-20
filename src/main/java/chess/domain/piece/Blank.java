package chess.domain.piece;

import java.util.Map;

public class Blank extends AbstractPiece {

    private static final double SCORE = 0;
    private static final String ERROR_MESSAGE = "해당 위치에 말이 존재하지 않습니다";
    private static final String SYMBOL = ".";

    public Blank() {
        super(Color.BLANK, Position.of(0, 0));
    }

    public Blank(Color color, Position position) {
        super(color, position);
    }

    @Override
    public String symbol() {
        return SYMBOL;
    }

    @Override
    public double score() {
        return SCORE;
    }

    @Override
    public Piece move(Position position, Map<Position, Piece> pieces) {
        throw new IllegalArgumentException(ERROR_MESSAGE);
    }
}
