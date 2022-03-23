package chess.controller;

import static chess.view.InputView.requestValidStartOrEndInput;
import static chess.view.OutputView.printGameInstructions;

import chess.domain.ChessGame;

public class GameController {

    private static final int EXIT_STATUS_CODE = 0;

    public ChessGame startGame() {
        printGameInstructions();
        if (!requestValidStartOrEndInput()) {
            System.exit(EXIT_STATUS_CODE);
        }
        return new ChessGame();
    }
}
