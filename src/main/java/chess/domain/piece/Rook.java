package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;

public class Rook extends MultiStepPiece {

    public Rook(Color color) {
        super(color, PieceType.ROOK, Direction.ofStraight());
    }
}
