package chess.controller;

import chess.domain.Board;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.function.Supplier;

public class ChessController {

    private final OutputView outputView;
    private final InputView inputView;

    public ChessController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void init() {
        GameCommand gameCommand = GameCommand.START;
        outputView.printInitialMessage();
        while (gameCommand != GameCommand.END) {
            gameCommand = repeat(() -> GameCommand.from(inputView.inputGameCommand()));
            if (gameCommand != GameCommand.END) {
                Board board = new Board();
                outputView.printBoard(board.getPiecePosition());
            }
        }
    }

    private <T> T repeat(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                outputView.printError(e);
            }
        }
    }
}
