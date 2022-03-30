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
    private final Map<GameCommand, Consumer<GameCommandRequest>> requestMapper
            = Map.of(GameCommand.START, this::start, GameCommand.MOVE, this::move,
            GameCommand.END, this::end, GameCommand.STATUS, this::status);

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
            requestMapper.get(request.getGameCommand()).accept(request);
        }
    }

    private void start(GameCommandRequest request) {
        OutputView.startGame();
        OutputView.printBoard(service.initBoard());
    }

    private void move(GameCommandRequest request) {
        List<String> body = request.getBody();
        OutputView.printBoard(service.move(body.get(FROM_INDEX), body.get(TO_INDEX)));
    }

    private void status(GameCommandRequest request) {
        OutputView.printStatus(service.getScores());
        this.end(request);
    }

    private void end(GameCommandRequest request) {
        OutputView.printEndMessage();
    }
}
