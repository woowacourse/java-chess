package chess.domain;

import chess.domain.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {

    public void run() {
        OutputView.printStartView();
        Command command = new Command(InputView.requestCommand());

        while (!command.isEnd()) {
            Board board = new Board();
            board.initialize();
            OutputView.printBoard(board);

            command = new Command(InputView.requestCommand());
        }
    }
}
