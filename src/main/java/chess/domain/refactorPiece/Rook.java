package chess.domain.refactorPiece;


import static chess.domain.refactorBoard.Direction.DOWN;
import static chess.domain.refactorBoard.Direction.LEFT;
import static chess.domain.refactorBoard.Direction.RIGHT;
import static chess.domain.refactorBoard.Direction.TOP;

import chess.domain.piece.attribute.Color;
import java.util.Arrays;

public class Rook extends MultipleMovablePiece {

    public Rook(Color color) {
        super(color, "R", Arrays.asList(TOP, DOWN, LEFT, RIGHT));
    }
}
