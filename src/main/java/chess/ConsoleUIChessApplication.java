package chess;

import chess.domain.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleUIChessApplication {
    public static void main(String[] args) {
        OutputView.start();
        OutputView.command();
        String command = InputView.command();
        Board board = Board.init();
        OutputView.board(board);
    }
}
