package chess.controller;

import chess.domain.ChessBoard;
import chess.util.BoardConverter;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        ChessBoard chessBoard = new ChessBoard();

        outputView.printStartMessage();
        Command command = inputView.requestGameCommand();

        if (command == Command.START) {
            outputView.printBoard(BoardConverter.convertToBoard(chessBoard.piecesByPosition()));
        }

    }
}
