package chess.domain.piece;

import java.util.Locale;

public class Pawn extends Piece {
    private static final String INITIAL_NAME = "P";
    public Pawn(final boolean isBlack) {
        super(isBlack, INITIAL_NAME);
    }
}
