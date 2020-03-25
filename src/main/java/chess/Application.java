package chess;

import chess.domain.board.Board;
import chess.domain.view.InputView;
import chess.domain.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView.instruction();
        if (InputView.getInput().equals("start")) {
            OutputView.showBoard(Board.init());
        }
    }
}
