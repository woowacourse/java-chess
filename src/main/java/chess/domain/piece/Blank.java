package chess.domain.piece;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Notation;
import chess.domain.piece.attribute.Score;

public class Blank extends Piece {
    private static final Notation BLANK_NOTATION = new Notation(".");
    private static final Blank BLANK = new Blank();
    private static final double ZERO_SCORE = 0;

    private Blank() {
        super(Color.BLANK, BLANK_NOTATION, moveOrder -> {
            throw new IllegalArgumentException("해당 칸에는 기물이 없습니다.");
        }, new Score(ZERO_SCORE));
    }

    public static Blank getInstance() {
        return BLANK;
    }
}
