package chess.domain.piece;

import static chess.domain.board.Direction.DOWN;
import static chess.domain.board.Direction.DOWN_LEFT;
import static chess.domain.board.Direction.DOWN_RIGHT;
import static chess.domain.board.Direction.LEFT;
import static chess.domain.board.Direction.RIGHT;
import static chess.domain.board.Direction.TOP;
import static chess.domain.board.Direction.TOP_LEFT;
import static chess.domain.board.Direction.TOP_RIGHT;

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

    @Override
    public boolean isPawn() {
        return false;
    }
}
