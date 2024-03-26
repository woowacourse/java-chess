package controller;

import controller.menu.Menu;
import java.util.function.Consumer;
import model.ChessGame;
import model.Command;
import view.InputView;
import view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ChessGame chessGame = new ChessGame();
        outputView.printStartMessage();
        readWithRetry(this::play, chessGame);
    }

    private void play(final ChessGame chessGame) {
        Menu menu;
        while (chessGame.isNotEnd()) {
            menu = Command.of(inputView.readCommandList());
            menu.play(chessGame, outputView);
        }
        outputView.printWinner(chessGame.calculateResult().getWinner());
    }

    private <T> T readWithRetry(final Consumer<ChessGame> consumer, final ChessGame chessGame) {
        try {
            consumer.accept(chessGame);
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return readWithRetry(consumer, chessGame);
        }
        return null;
    }
}
