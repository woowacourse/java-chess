package chess.console.controller;

import chess.console.service.ChessService;
import chess.console.view.InputView;
import chess.console.view.OutputView;
import java.util.List;

public final class ChessController {

    private static final int FROM_INDEX = 0;
    private static final int TO_INDEX = 1;

    private final ChessService service;

    public ChessController(ChessService service) {
        this.service = service;
    }

    public void run() {
        while (service.isWaitingOrRunning()) {
            runUntilValid(this::executeByInput);
        }
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

    private void executeByInput() {
        GameCommandRequest request = GameCommandRequest.of(InputView.inputCommandRequest());
        GameCommand gameCommand = request.getGameCommand();
        gameCommand.executeRequest(this, request);
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
        service.endGame();
        OutputView.printWinner(service.getResult());
        OutputView.printEndMessage();
    }
}
