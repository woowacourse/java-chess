package chess.domain.piece;

import static chess.domain.board.Direction.DOWN_DOWN_LEFT;
import static chess.domain.board.Direction.DOWN_DOWN_RIGHT;
import static chess.domain.board.Direction.DOWN_LEFT_LEFT;
import static chess.domain.board.Direction.DOWN_RIGHT_RIGHT;
import static chess.domain.board.Direction.TOP_LEFT_LEFT;
import static chess.domain.board.Direction.TOP_RIGHT_RIGHT;
import static chess.domain.board.Direction.TOP_TOP_LEFT;
import static chess.domain.board.Direction.TOP_TOP_RIGHT;

import chess.domain.piece.attribute.Color;
import java.util.Arrays;

public class Knight extends FixedMovablePiece {

    public Knight(Color color) {
        super(color, "N", Arrays.asList(
                TOP_TOP_RIGHT, TOP_TOP_LEFT, TOP_RIGHT_RIGHT, TOP_LEFT_LEFT,
                DOWN_DOWN_RIGHT, DOWN_DOWN_LEFT, DOWN_RIGHT_RIGHT, DOWN_LEFT_LEFT
        ));
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
