package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;

public class King extends SingleStepPiece {

    public King(Color color) {
        super(color, PieceType.KING, Direction.ALL);
    }
}
