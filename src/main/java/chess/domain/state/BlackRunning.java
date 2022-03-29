package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;

public class BlackRunning extends Running {

    public BlackRunning(Board board) {
        super(board, Color.BLACK);
    }

    @Override
    ChessState changeTurn() {
        return new WhiteRunning(board);
    }
}
