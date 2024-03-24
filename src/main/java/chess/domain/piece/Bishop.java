package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;

public class Bishop extends MultiStepPiece {
    public static final Bishop WHITE = new Bishop(Color.WHITE);
    public static final Bishop BLACK = new Bishop(Color.BLACK);

    private Bishop(Color color) {
        super(color, PieceType.BISHOP, Direction.DIAGONAL);
    }
}
