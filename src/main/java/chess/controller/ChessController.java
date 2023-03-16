package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.TeamColor;
import chess.dto.CommandRequest;
import chess.util.BoardConverter;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private TeamColor currentColor;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        ChessBoard chessBoard = new ChessBoard();
        currentColor = TeamColor.WHITE;

        outputView.printStartMessage();
        boolean isContinue = true;
        while (isContinue) {
            isContinue = play(chessBoard, currentColor);
        }
    }

    private boolean play(final ChessBoard chessBoard, final TeamColor color) {
        CommandRequest commandRequest = inputView.requestGameCommand();
        if (commandRequest.getCommand() == Command.END) {
            return false;
        }
        if (commandRequest.getCommand() == Command.MOVE) {
            chessBoard.move(commandRequest.getSourceCoordinate(), commandRequest.getDestinationCoordinate(), color);
            currentColor = currentColor.transfer();
        }
        outputView.printBoard(BoardConverter.convertToBoard(chessBoard.piecesByPosition()));
        return true;
    }
}
