package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.PieceType;

public class King extends SingleStepPiece {

    public King(Color color) {
        super(color, PieceType.KING, Direction.ofAll());
    }
}
