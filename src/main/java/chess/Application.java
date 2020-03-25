package chess;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.Run;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Run run = InputView.inputStart();
        if (run.isStart()) {
            Board board = BoardFactory.createBoard();
            OutputView.printInitializedBoard(board);
        }

    }
}
