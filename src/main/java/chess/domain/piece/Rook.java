package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.PieceType;

public class Rook extends MultiStepPiece {

    public Rook(Color color) {
        super(color, PieceType.ROOK, Direction.ofStraight());
    }
}
