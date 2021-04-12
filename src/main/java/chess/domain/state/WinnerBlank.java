package chess.domain.state;

import chess.domain.board.Pieces;
import chess.domain.piece.Color;

public abstract class WinnerBlank extends AbstractState {

    protected WinnerBlank(final Color color, final Pieces pieces) {
        super(color, pieces);
    }

    @Override
    public Color winner() {
        return null;
    }
}
