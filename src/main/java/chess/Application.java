package chess;

import chess.domain.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Command command = InputView.requestCommand();
        if (command.isStart()) {
            Board board = new Board();
            OutputView.printChessBoard(board);
        }
    }
}
