package chess.controller;

import static chess.Command.*;

import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printInitMessage();
        while (true) {
            if (InputView.inputCommand() == END) {
                return;
            }

            OutputView.printChessGameBoard();
        }
    }
}
