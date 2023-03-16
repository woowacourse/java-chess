package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.TeamColor;
import chess.dto.ChessBoardDto;
import chess.dto.CommandRequest;
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
        this.currentColor = TeamColor.WHITE;
    }

    public void run() {
        ChessBoard chessBoard = new ChessBoard();

        outputView.printStartMessage();
        boolean isContinue = true;
        while (isContinue) {
            isContinue = play(chessBoard);
        }
    }

    private boolean play(final ChessBoard chessBoard) {
        CommandRequest commandRequest = inputView.requestGameCommand();
        if (commandRequest.getCommand() == Command.END) {
            return false;
        }
        if (commandRequest.getCommand() == Command.MOVE) {
            progressMove(chessBoard, commandRequest);
        }
        outputView.printBoard(ChessBoardDto.from(chessBoard));
        return true;
    }

    private void progressMove(ChessBoard chessBoard, CommandRequest commandRequest) {
        chessBoard.move(commandRequest.getSource(), commandRequest.getDestination(), currentColor);
        currentColor = currentColor.transfer();
    }

}
