package chess.controller;

import chess.command.Command;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardMaker;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        if (inputView.readStartCommand() == Command.START) {
            startGame();
        }
    }

    private void startGame() {
        ChessBoardMaker chessBoardMaker = new ChessBoardMaker();
        ChessBoard chessBoard = chessBoardMaker.make();
        outputView.printChessBoard(chessBoard.getSquares());
    }
}
