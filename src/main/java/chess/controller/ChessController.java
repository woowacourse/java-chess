package chess.controller;

import chess.domain.game.Command;
import chess.domain.game.GameState;
import chess.domain.game.Ready;
import chess.dto.BoardAndTurnInfo;
import chess.dto.Request;
import chess.dto.ScoreResponse;
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
        }
    }

    private GameState tryExecute(GameState gameState) {
        try {
            Request request = Request.of(inputView.inputCommand());
            Command command = Command.find(request.getCommand());
            return runGameByCommand(gameState, request, command);
        } catch (RuntimeException e) {
            outputView.printException(e);
            return tryExecute(gameState);
        }
    }

    private GameState runGameByCommand(GameState gameState, Request request, Command command) {
        if (command.isType(Command.START)) {
            GameState state = gameState.start();
            outputView.printBoardAndTurn((BoardAndTurnInfo) state.getResponse());
            return state;
        }
        if (command.isType(Command.FINISH)) {
            return gameState.finish();
        }
        if (command.isType(Command.MOVE)) {
            GameState state = gameState.move(request.getArguments());
            outputView.printBoardAndTurn((BoardAndTurnInfo) state.getResponse());
            return state;
        }
        if (command.isType(Command.STATUS)) {
            GameState state = gameState.status();
            outputView.printStatus((ScoreResponse) state.getResponse());
            return state;
        }
        return null;
    }
}
