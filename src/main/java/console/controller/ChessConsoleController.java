package console.controller;

import chess.domain.board.Point;
import chess.domain.game.GameState;
import chess.domain.game.Ready;
import chess.dto.BoardAndTurnInfo;
import chess.dto.ScoreResponse;
import console.view.InputView;
import console.view.OutputView;

import java.util.List;

public class ChessConsoleController {

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
            OutputView.printEnterCommand();
            Request request = Request.of(InputView.inputCommand());
            Command command = Command.find(request.getCommand());
            return command.execute(gameState, request.getArguments());
        } catch (RuntimeException e) {
            OutputView.printException(e);
            return tryExecute(gameState);
        }
    }

    static GameState start(GameState gameState, List<Point> ignored) {
        GameState state = gameState.start();
        OutputView.printBoardAndTurn((BoardAndTurnInfo) state.getResponse());
        return state;
    }

    static GameState finish(GameState gameState, List<Point> ignored) {
        return gameState.finish();
    }

    static GameState move(GameState gameState, List<Point> arguments) {
        GameState state = gameState.move(arguments);
        OutputView.printBoardAndTurn((BoardAndTurnInfo) state.getResponse());
        return state;
    }

    static GameState status(GameState gameState, List<Point> ignored) {
        GameState state = gameState.status();
        OutputView.printStatus((ScoreResponse) state.getResponse());
        return state;
    }
}
