package chess.controller;

import chess.domain.ChessBoard;
import chess.dto.CommandRequest;
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
        CommandRequest commandRequest;
        boolean isContinue = true;
        while (isContinue) {
            isContinue = play(chessBoard);
        }
    }

    private boolean play(final ChessBoard chessBoard) {
        CommandRequest commandRequest;
        commandRequest = inputView.requestGameCommand();
        if (commandRequest.getCommand() == Command.END) {
            return false;
        }
        if (commandRequest.getCommand() == Command.MOVE) {
            // TODO move
        }
        outputView.printBoard(BoardConverter.convertToBoard(chessBoard.piecesByPosition()));
        return true;
    }
}
