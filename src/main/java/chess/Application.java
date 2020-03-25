package chess;

import chess.domains.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static final String START = "start";

    public static void main(String[] args) {
        String startMsg = InputView.inputStart();
        if (START.equals(startMsg)) {
            OutputView.printBoard(new Board().showBoard());
        }
        OutputView.printEnd();
    }

}
