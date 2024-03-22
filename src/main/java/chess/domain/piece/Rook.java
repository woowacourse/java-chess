package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;

public class Rook extends MultiStepPiece {
    public static final Rook WHITE = new Rook(Color.WHITE);
    public static final Rook BLACK = new Rook(Color.BLACK);

    private Rook(Color color) {
        super(color, PieceType.ROOK, Direction.STRAIGHT);
    }
}
