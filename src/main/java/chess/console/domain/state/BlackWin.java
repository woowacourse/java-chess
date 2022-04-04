package chess.console.domain.state;

import chess.console.domain.board.Board;
import chess.console.domain.piece.Color;

public class BlackWin extends Finished {

    BlackWin(Board board) {
        super(board);
    }

    @Override
    public Color getTurn() {
        return Color.WHITE;
    }
}
