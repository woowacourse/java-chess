package chess.domain.state;

import chess.domain.board.Pieces;
import chess.domain.piece.Color;

public class Finish extends AbstractState {

    public Finish(final Color color, final Pieces pieces) {
        super(color, pieces);
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
