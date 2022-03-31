package chess.domain.refactorPiece;

import static chess.domain.refactorBoard.Direction.DOWN_LEFT;
import static chess.domain.refactorBoard.Direction.DOWN_RIGHT;
import static chess.domain.refactorBoard.Direction.TOP_LEFT;
import static chess.domain.refactorBoard.Direction.TOP_RIGHT;

import chess.domain.piece.attribute.Color;
import java.util.Arrays;

public class Bishop extends MultipleMovablePiece {

    public Bishop(Color color) {
        super(color, "B", Arrays.asList(TOP_LEFT, TOP_RIGHT, DOWN_LEFT, DOWN_RIGHT));
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
