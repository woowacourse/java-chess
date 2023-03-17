package chess.controller;

import chess.game.action.Action;
import chess.game.action.ExitAction;
import chess.game.GameStatus;
import chess.game.action.MoveAction;
import chess.game.action.StartAction;
import chess.game.GameCommand;
import chess.domain.Position;
import chess.dto.PositionRequest;
import chess.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PositionMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ChessController {
    private static final String START_COMMAND = "start";
    private static final String MOVE_COMMAND = "move";
    private static final String END_COMMAND = "end";
    private final Map<String, Action> actionMap = new HashMap<>();
    private final ChessGame chessGame;

    public ChessController(ChessGame chessGame) {
        this.chessGame = chessGame;
        actionMap.put(START_COMMAND, new StartAction(this::createBoard));
        actionMap.put(MOVE_COMMAND, new MoveAction(this::move));
        actionMap.put(END_COMMAND, new ExitAction());
    }

    private void createBoard() {
        OutputView.printBoard(chessGame.getBoard());
    }

    private void move(GameCommand gameCommand) {
        printError(() -> {
            PositionRequest source = PositionMapper.map(gameCommand.getParameter(0));
            PositionRequest target = PositionMapper.map(gameCommand.getParameter(1));
            chessGame.move(Position.of(source.getX(), source.getY()), Position.of(target.getX(), target.getY()));
            OutputView.printBoard(chessGame.getBoard());
        });
    }

    private void printError(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    public void run() {
        OutputView.printStartMessage();
        while (gameLoop() == GameStatus.CONTINUE) {
        }
    }

    private GameStatus gameLoop() {
        GameCommand command = printErrorAndRetry(InputView::readCommand);
        return actionMap.get(command.getCommand()).execute(command);
    }

    private <T> T printErrorAndRetry(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
            return printErrorAndRetry(supplier);
        }
    }
}
