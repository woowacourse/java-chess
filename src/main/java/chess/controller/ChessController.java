package chess.controller;

import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public void run() {
        outputView.printStartMessage();
        final ChessExecuteCommand chessExecuteCommand = ChessExecuteCommand.from(inputView.readChessExecuteCommand());
    }
}
