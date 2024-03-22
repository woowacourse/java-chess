package chess.domain.piece;


import chess.domain.Color;
import chess.domain.Direction;

public class Queen extends MultiStepPiece {

    public Queen(Color color) {
        super(color, PieceType.QUEEN, Direction.ALL);
    }
}
