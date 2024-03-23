package chess.domain.piece.implement;

import chess.domain.board.Path;
import chess.domain.piece.Color;

public class WhitePawn extends Pawn {

    public WhitePawn() {
        super(Color.WHITE);
    }

    @Override
    boolean isBackward(Path path) {
        return path.isDownside();
    }
}
