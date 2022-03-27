package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        ChessGame chessGame = new ChessGame();

        while (true) {
            String command = inputView.inputCommand();
            if ("start".equals(command)) {
                chessGame.start();
                outputView.printBoard(chessGame.getBoard().getValue());
            }
            if ("end".equals(command)) {
                if (!chessGame.isRunning()) {
                    break;
                }
                chessGame.end();
            }
        }
    }
}
