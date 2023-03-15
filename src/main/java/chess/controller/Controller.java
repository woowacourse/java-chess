package chess.controller;

import chess.domain.Board;
import chess.view.GameState;
import chess.view.InputView;
import chess.view.OutputView;

public class Controller {
    public void run() {
        OutputView.printStartMessage();
        while (InputView.inputGameState() != GameState.END) {
            OutputView.printChessBoard(Board.create().getBoard());
        }
    }
}
