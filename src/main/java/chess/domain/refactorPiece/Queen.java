package chess.domain.refactorPiece;

import static chess.domain.refactorBoard.Direction.DOWN;
import static chess.domain.refactorBoard.Direction.DOWN_LEFT;
import static chess.domain.refactorBoard.Direction.DOWN_RIGHT;
import static chess.domain.refactorBoard.Direction.LEFT;
import static chess.domain.refactorBoard.Direction.RIGHT;
import static chess.domain.refactorBoard.Direction.TOP;
import static chess.domain.refactorBoard.Direction.TOP_LEFT;
import static chess.domain.refactorBoard.Direction.TOP_RIGHT;

import chess.domain.piece.attribute.Color;
import java.util.Arrays;

public class Queen extends MultipleMovablePiece {

    public Queen(Color color) {
        super(color, "Q", Arrays.asList(TOP, DOWN, RIGHT, LEFT, TOP_RIGHT, TOP_LEFT, DOWN_RIGHT, DOWN_LEFT));
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
