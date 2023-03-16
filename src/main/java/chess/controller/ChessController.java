package chess.controller;

import chess.domain.Camp;
import chess.domain.ChessBoard;
import chess.dto.CommandRequest;
import chess.util.BoardConverter;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private Camp currentTurnCamp;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        ChessBoard chessBoard = new ChessBoard();
        currentTurnCamp = Camp.WHITE;

        outputView.printStartMessage();
        boolean isContinue = true;
        while (isContinue) {
            isContinue = play(chessBoard, currentTurnCamp);
        }
    }

    private boolean play(final ChessBoard chessBoard, final Camp camp) {
        CommandRequest commandRequest = inputView.requestGameCommand();
        if (commandRequest.getCommand() == Command.END) {
            return false;
        }
        if (commandRequest.getCommand() == Command.MOVE) {
            chessBoard.move(commandRequest.getSourceCoordinate(), commandRequest.getDestinationCoordinate(), camp);
            currentTurnCamp = currentTurnCamp.transfer();
        }
        outputView.printBoard(BoardConverter.convertToBoard(chessBoard.piecesByPosition()));
        return true;
    }
}
