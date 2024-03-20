package chess.controller;

import chess.model.ChessBoard;
import chess.model.ChessBoardInitializer;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.MoveArguments;
import chess.view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        GameCommand gameCommand = inputView.readGameCommand();
        if (gameCommand.isEnd()) {
            return;
        }
        ChessBoardInitializer initializer = new ChessBoardInitializer();
        ChessBoard chessBoard = new ChessBoard(initializer.create());
        outputView.printChessBoard(chessBoard);
        MoveArguments moveArguments = inputView.readMoveArguments();
    }
}
