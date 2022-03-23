package chess.controller;

import static chess.view.InputView.requestValidMoveInput;
import static chess.view.InputView.requestValidStartOrEndInput;
import static chess.view.InputView.requestValidStatusOrEndInput;
import static chess.view.OutputView.printBoard;
import static chess.view.OutputView.printGameInstructions;
import static chess.view.OutputView.printGameOverInstructions;
import static chess.view.OutputView.printStatus;

import chess.domain.ChessGame;
import chess.dto.BoardDto;

public class GameController {

    private static final int EXIT_STATUS_CODE = 0;

    public ChessGame startGame() {
        printGameInstructions();
        if (!requestValidStartOrEndInput()) {
            System.exit(EXIT_STATUS_CODE);
        }
        return new ChessGame();
    }

    public void playGame(ChessGame game) {
        printBoard(new BoardDto(game));
        while (!game.isEnd()) {
            game.moveChessmen(requestValidMoveInput());
            printBoard(new BoardDto(game));
        }
    }

    public void endGame(ChessGame game) {
        printGameOverInstructions();
        while (requestValidStatusOrEndInput()) {
            printStatus(game.getGameResult());
        }
    }
}
