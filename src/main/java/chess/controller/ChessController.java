package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.StartCommand;
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
        final ChessBoard chessBoard = startChess();

        List<String> positions = inputView.readMoveCommand();

        while (isNotEndCommand(positions)) {
            playTurn(chessBoard, positions);

            positions = inputView.readMoveCommand();
        }
    }

    private ChessBoard startChess() {
        final ChessBoard chessBoard = ChessBoard.init();

        if (isCommandStart()) {
            outputView.printChessBoard(chessBoard.getPieces());
        }

        return chessBoard;
    }

    private boolean isCommandStart() {
        StartCommand startCommand = StartCommand.from(inputView.readStartCommand());
        return startCommand.equals(StartCommand.START);
    }

    private boolean isNotEndCommand(final List<String> validInputPositions) {
        return !validInputPositions.isEmpty();
    }

    private void playTurn(final ChessBoard chessBoard, final List<String> positions) {
        chessBoard.move(positions);
        outputView.printChessBoard(chessBoard.getPieces());
    }
}
