package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;

public class Rook extends MultiStepPiece {
    public static final Rook BLACK = new Rook(Color.BLACK);
    public static final Rook WHITE = new Rook(Color.WHITE);

    private Rook(Color color) {
        super(color, PieceType.ROOK, Direction.ofStraight);
    }
}
