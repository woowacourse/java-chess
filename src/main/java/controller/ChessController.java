package controller;

import dto.GameBoardDto;
import java.util.function.Consumer;
import model.ChessGame;
import model.Command;
import model.menu.Menu;
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
        Menu status = Command.of(inputView.readCommandList());
        status.play(chessGame);
        while (chessGame.isNotEnd()) {
            printCurrentStatus(chessGame);
            status = Command.of(inputView.readCommandList());
            status.play(chessGame);
        }
    }

    private void printCurrentStatus(final ChessGame chessGame) {
        outputView.printGameBoard(GameBoardDto.from(chessGame));
        outputView.printCurrentCame(chessGame.getCamp());
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
