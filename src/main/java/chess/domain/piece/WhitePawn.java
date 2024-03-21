package chess.domain.piece;

import chess.domain.board.Path;

public class WhitePawn extends Pawn {

    public WhitePawn() {
        super(Color.WHITE);
    }

    @Override
    boolean isBackward(Path path) {
        return path.isDownside();
    }
}
