package chess.controller;

import chess.domain.ChessBoard;
import chess.view.GameStartCommand;
import chess.view.InputView;
import chess.view.OutputView;

// TODO: 이름 고민 - er??
public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final ChessBoard chessBoard = new ChessBoard();

        GameStartCommand gameStartCommand = inputView.readGameStartCommand();
        if (GameStartCommand.START.equals(gameStartCommand)) {
            outputView.printChessBoard(chessBoard);
        }
    }
}
