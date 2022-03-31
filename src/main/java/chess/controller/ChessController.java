package chess.controller;

import chess.controller.state.GameState;
import chess.controller.state.Ready;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public final class ChessController {

    private static final int FROM_INDEX = 0;
    private static final int TO_INDEX = 1;
    private final ChessService service;

    public ChessController(ChessService service) {
        this.service = service;
    }

    private void runUntilValid(Runnable runner) {
        boolean runSuccess;
        do {
            runSuccess = tryRun(runner);
        } while (!runSuccess);
    }

    private boolean tryRun(Runnable runner) {
        try {
            runner.run();
            return true;
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printException(e);
            return false;
        }
    }

    public void run() {
        runUntilValid(this::playGame);
    }

    private void playGame() {
        GameState gameState = new Ready();
        while (gameState.isRunning(service)) {
            GameCommandRequest request = GameCommandRequest.of(InputView.inputStartOrEndGame());
            gameState = gameState.execute(request.getGameCommand());
            request.getGameCommand().executeRequest(this, request);
        }
    }

    public void start(GameCommandRequest request) {
        OutputView.startGame();
        OutputView.printBoard(service.initGame());
    }

    public void move(GameCommandRequest request) {
        List<String> body = request.getBody();
        OutputView.printBoard(service.move(body.get(FROM_INDEX), body.get(TO_INDEX)));
    }

    public void status(GameCommandRequest request) {
        OutputView.printStatus(service.getScores());
        this.end(request);
    }

    public void end(GameCommandRequest request) {
        OutputView.printEndMessage();
    }
}
