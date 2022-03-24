package chess;

import chess.domain.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    public void run() {
        OutputView.announceStart();
        Board board = new Board();
        while (InputView.isStart()) {
            OutputView.showBoard(board.splitByRank());
        }
    }
}
