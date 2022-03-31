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

    public void run() {
        OutputView.printIntroduction();
        playGame();
        OutputView.printEnd();
    }

    private void playGame() {
        GameState gameState = new Ready();
        while (gameState.isRunnable()) {
            gameState = tryExecute(gameState);
        }
    }

    private GameState tryExecute(GameState gameState) {
        try {
            Request request = Request.of(InputView.inputCommand());
            Command command = Command.find(request.getCommand());
            return runGameByCommand(gameState, request, command);
        } catch (RuntimeException e) {
            OutputView.printException(e);
            return tryExecute(gameState);
        }
    }

    private GameState runGameByCommand(GameState gameState, Request request, Command command) {
        if (command.isType(Command.START)) {
            GameState state = gameState.start();
            OutputView.printBoardAndTurn((BoardAndTurnInfo) state.getResponse());
            return state;
        }
        if (command.isType(Command.FINISH)) {
            return gameState.finish();
        }
        if (command.isType(Command.MOVE)) {
            GameState state = gameState.move(request.getArguments());
            OutputView.printBoardAndTurn((BoardAndTurnInfo) state.getResponse());
            return state;
        }
        if (command.isType(Command.STATUS)) {
            GameState state = gameState.status();
            OutputView.printStatus((ScoreResponse) state.getResponse());
            return state;
        }
        return null;
    }
}
