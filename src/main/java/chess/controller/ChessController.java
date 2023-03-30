package chess.controller;

import chess.controller.state.GameState;
import chess.controller.state.ProgressState;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start(final ChessService chessService) {
        inputView.printGameStartMessage();
        doGame(chessService);
    }

    private void doGame(final ChessService chessService) {
        GameState state = retry(() -> getGameState(chessService));
        retry(this::progressGame, state);
    }

    private GameState getGameState(final ChessService chessService) {
        final GameCommand gameCommand = retry(inputView::readGameCommand);
        return getGameState(gameCommand, chessService);
    }

    private GameState getGameState(final GameCommand gameCommand, final ChessService chessService) {
        GameState state = ProgressState.of(gameCommand, chessService);
        printCreateGameMessage(state);
        printBoardStatus(state);
        return state;
    }

    private void printCreateGameMessage(final GameState state) {
        outputView.printIfHasGame(state.hasGame());
    }

    private void printBoardStatus(final GameState state) {
        state.printBoardStatus(outputView);
    }

    private <T> T retry(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void progressGame(GameState state) {
        while (state.isNotEnd()) {
            final MoveRequest moveRequest = readGameCommand();

            state = applyCommandAndExecute(state, moveRequest);

            printGameProgressMessage(state.isGameEnd());
        }
    }

    private MoveRequest readGameCommand() {
        return inputView.readPlayCommand();
    }

    private static GameState applyCommandAndExecute(GameState state, final MoveRequest moveRequest) {
        state = state.changeState(moveRequest.getGameCommand());
        state.executeAndSave(moveRequest.getSource(), moveRequest.getTarget());
        return state;
    }

    private void printGameProgressMessage(final GameState state) {
        state.printScores(outputView);
        state.printBoardStatus(outputView);
    }

    private <T> void retry(final Consumer<T> consumer, T input) {
        while (true) {
            try {
                consumer.accept(input);
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
