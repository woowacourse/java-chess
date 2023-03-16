package chess.controller;

import chess.domain.Board;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.function.Supplier;

public class ChessController {

    private final OutputView outputView;
    private final InputView inputView;
    private final Board board = new Board();

    public ChessController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

//    public boolean realInit() {
////        GameCommand gameCommand = readCommand();
////        if (gameCommand == GameCommand.END) {
////            return;
////        }
////        start();
//    }

    public void init() {
        final GameCommand gameCommand = GameCommand.START;
        outputView.printInitialMessage();
//        while (GameCommand.END.isCommandOf(readCommand())) {
//            gameCommand = repeat(() -> GameCommand.from(inputView.inputGameCommand()));
//            if (gameCommand != GameCommand.END) {
//                Board board = new Board();
//                outputView.printBoard(board.getPiecePosition());
//            }
//        }
    }

//    private String readCommand() {
//        repeat(() -> GameCommand.from(inputView.inputGameCommand()));
//    }

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
