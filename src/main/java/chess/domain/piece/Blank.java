package chess.domain.piece;

import chess.domain.position.Position;

public class Blank extends Piece {
    private static final String BLANK_NOTATION = ".";

    public Blank(Position position) {
        super(position, BLANK_NOTATION);
    }
}
