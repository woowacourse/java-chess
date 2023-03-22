package chess.controller;

import chess.model.board.state.GameState;
import chess.model.board.state.Start;
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

    public void start() {
        inputView.printGameStartMessage();
        doGame();
    }

    private void doGame() {
        GameState state = retry(this::getGameState);
        retry(this::progressGame, state);
    }

    private GameState getGameState() {
        final GameCommand gameCommand = retry(inputView::readGameCommand);
        return getGameState(gameCommand);
    }

    private GameState getGameState(final GameCommand gameCommand) {
        GameState state = Start.from(gameCommand);
        printBoardStatus(state);
        return state;
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
            state = updateAndCheckEnd(state, moveRequest);
            printBoardStatus(state);
        }
    }

    private void printBoardStatus(final GameState state) {
        if (state.isNotEnd()) {
            outputView.printChessBoard(new BoardResponse(state.getBoard()));
        }
    }

    private MoveRequest readGameCommand() {
        return inputView.readPlayCommand();
    }

    private GameState updateAndCheckEnd(GameState state, final MoveRequest moveRequest) {
        state = state.execute(
                moveRequest.getGameCommand(), moveRequest.getSource(), moveRequest.getTarget()
        );
        return state.isGameEnd();
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
