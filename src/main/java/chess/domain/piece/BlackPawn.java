package chess.domain.piece;

import chess.domain.board.Path;

public class BlackPawn extends Pawn {
    public BlackPawn() {
        super(Color.BLACK);
    }

    @Override
    boolean isBackward(Path path) {
        return path.isUpside();
    }
}
