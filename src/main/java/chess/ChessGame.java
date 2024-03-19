package chess;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.view.OutputView;

public class ChessGame {
    public void run() {
        Board board = BoardFactory.createInitialBoard();
        OutputView.printGameStartMessage();
        OutputView.printChessBoard(board);
    }
}
