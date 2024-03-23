package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;

public class Knight extends SingleStepPiece {
    public static final Knight BLACK = new Knight(Color.BLACK);
    public static final Knight WHITE = new Knight(Color.WHITE);

    private Knight(Color color) {
        super(color, PieceType.KNIGHT, Direction.ofKnight);
    }
}
