package chess.controller;

import chess.model.ChessBoard;
import chess.model.ChessBoardInitializer;
import chess.view.GameCommand;
import chess.view.InputView;

public class ChessController {
    private final InputView inputView;

    public ChessController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        GameCommand gameCommand = inputView.readGameCommand();
        if(gameCommand.isEnd()) {
            return;
        }
        ChessBoardInitializer initializer = new ChessBoardInitializer();
        ChessBoard chessBoard = new ChessBoard(initializer.create());
    }
}
