package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.Command;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void runChess() {
        final ChessBoard chessBoard = ChessBoard.init();
        startChess(chessBoard);

        List<String> positions = inputView.readMoveCommand();

        while (isNotEndCommand(positions)) {
            playTurn(chessBoard, positions);

            positions = inputView.readMoveCommand();
        }
    }

    private void startChess(final ChessBoard chessBoard) {
        if (isCommandStart()) {
            outputView.printChessBoard(chessBoard.getPieces());
        }
    }

    private boolean isCommandStart() {
        Command command = Command.fromStartCommand(inputView.readStartCommand());
        return command.equals(Command.START);
    }

    private boolean isNotEndCommand(final List<String> validInputPositions) {
        return !validInputPositions.isEmpty();
    }

    private void playTurn(final ChessBoard chessBoard, final List<String> positions) {
        chessBoard.move(positions);
        outputView.printChessBoard(chessBoard.getPieces());
    }
}
