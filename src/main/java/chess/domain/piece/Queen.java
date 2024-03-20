package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.PieceType;

public class Queen extends MultiStepPiece {

    public Queen(Color color) {
        super(color, PieceType.QUEEN, Direction.ofAll());
    }
}
