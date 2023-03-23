package chess.controller;

import chess.model.Scores;
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

            state = applyCommandAndExecute(state, moveRequest);

            printGameProgressMessage(state.isGameEnd());
        }
    }

    private MoveRequest readGameCommand() {
        return inputView.readPlayCommand();
    }

    private static GameState applyCommandAndExecute(GameState state, final MoveRequest moveRequest) {
        state = state.changeState(moveRequest.getGameCommand());
        state.execute(moveRequest.getSource(), moveRequest.getTarget());
        return state;
    }

    private void printGameProgressMessage(final GameState state) {
        printScores(state);
        printBoardStatus(state);
    }

    private void printScores(final GameState state) {
        if (state.isStatus()) {
            final Scores scores = state.calculateScores();
            outputView.printScores(ScoreResponses.from(scores));
        }
    }

    private void printBoardStatus(final GameState state) {
        if (state.isNotEnd()) {
            outputView.printChessBoard(new BoardResponse(state.getBoard()));
        }
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
