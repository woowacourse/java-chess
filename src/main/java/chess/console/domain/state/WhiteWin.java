package chess.console.domain.state;

import chess.console.domain.board.Board;
import chess.console.domain.piece.Color;

public class WhiteWin extends Finished {

    WhiteWin(Board board) {
        super(board);
    }

    @Override
    public Color getTurn() {
        return Color.BLACK;
    }
}
