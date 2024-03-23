package chess.domain.piece.implement;

import chess.domain.board.Path;
import chess.domain.piece.Color;
import chess.domain.piece.implement.Pawn;

public class WhitePawn extends Pawn {

    public WhitePawn() {
        super(Color.WHITE);
    }

    @Override
    boolean isBackward(Path path) {
        return path.isDownside();
    }
}
