package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;

public class BlackWin extends Finished {

    BlackWin(Board board) {
        super(board);
    }

    @Override
    public Color getTurn() {
        return Color.WHITE;
    }
}
