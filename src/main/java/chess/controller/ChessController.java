package chess.controller;

import chess.domain.game.Command;
import chess.domain.game.GameState;
import chess.domain.game.Ready;
import chess.dto.Request;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputVIew) {
        this.inputView = inputView;
        this.outputView = outputVIew;
    }

    public void run() {
        outputView.printIntroduction();
        playGame();
        outputView.printEnd();
    }

    private void playGame() {
        GameState gameState = new Ready();
        while (gameState.isRunnable()) {
            gameState = tryExecute(gameState);
            outputView.printResponse(gameState.getResponse());
        }
    }

    private GameState tryExecute(GameState gameState) {
        try {
            Request request = Request.of(inputView.inputCommand());
            Command command = Command.find(request.getCommand());
            return command.execute(gameState, request.getArguments());
        } catch (RuntimeException e) {
            outputView.printException(e);
            return tryExecute(gameState);
        }
    }
}
