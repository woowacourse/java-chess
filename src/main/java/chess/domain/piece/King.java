package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;

public class King extends SingleStepPiece {
    public static final King WHITE = new King(Color.WHITE);
    public static final King BLACK = new King(Color.BLACK);

    private King(Color color) {
        super(color, PieceType.KING, Direction.ALL);
    }
}
