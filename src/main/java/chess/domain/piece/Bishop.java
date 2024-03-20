package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;

public class Bishop extends MultiStepPiece {

    public Bishop(Color color) {
        super(color, PieceType.BISHOP, Direction.ofDiagonal());
    }
}
