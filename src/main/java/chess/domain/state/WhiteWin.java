package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;

public class WhiteWin extends Finished {

    WhiteWin(Board board) {
        super(board);
    }

    @Override
    public Color getTurn() {
        return Color.BLACK;
    }
}
