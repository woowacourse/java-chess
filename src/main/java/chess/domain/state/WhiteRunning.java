package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;

public class WhiteRunning extends Running {

    public WhiteRunning(Board board) {
        super(board, Color.WHITE);
    }

    @Override
    ChessState changeTurn() {
        return new BlackRunning(board);
    }
}
