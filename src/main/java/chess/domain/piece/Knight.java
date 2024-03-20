package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.PieceType;

public class Knight extends SingleStepPiece {

    public Knight(Color color) {
        super(color, PieceType.KNIGHT, Direction.ofKnight());
    }
}
