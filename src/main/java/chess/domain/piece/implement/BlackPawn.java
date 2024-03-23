package chess.domain.piece.implement;

import chess.domain.board.Path;
import chess.domain.piece.Color;

public class BlackPawn extends Pawn {
    public BlackPawn() {
        super(Color.BLACK);
    }

    @Override
    boolean isBackward(Path path) {
        return path.isUpside();
    }
}
