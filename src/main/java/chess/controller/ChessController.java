package chess.controller;

import chess.view.InputView;

public class ChessController {

    private static final InputView inputView = new InputView();

    public void run() {
        final ChessExecuteCommand chessExecuteCommand = ChessExecuteCommand.from(inputView.readChessExecuteCommand());
    }
}
