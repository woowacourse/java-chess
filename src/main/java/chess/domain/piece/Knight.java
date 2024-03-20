package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;

public class Knight extends SingleStepPiece {

    public Knight(Color color) {
        super(color, PieceType.KNIGHT, Direction.ofKnight());
    }
}
