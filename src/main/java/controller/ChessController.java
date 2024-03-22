package controller;

import domain.ChessBoard;
import domain.command.ChessCommand;
import view.InputView;
import view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        final ChessBoard chessBoard = ChessBoard.create();
        final ChessCommand startChessCommand = readStartCommand();

        if (startChessCommand.run(chessBoard)) {
            outputView.printChessTable(chessBoard.getPieceSquares());
            play(chessBoard);
        }
    }

    private ChessCommand readStartCommand() {
        try {
            return inputView.readStartCommand();
        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return readStartCommand();
        }
    }

    private void play(final ChessBoard chessBoard) {
        try {
            while (readMoveCommand().run(chessBoard)) {
                outputView.printChessTable(chessBoard.getPieceSquares());
            }
        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            play(chessBoard);
        }
    }

    private ChessCommand readMoveCommand() {
        try {
            return inputView.readMoveCommand();
        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return readMoveCommand();
        }
    }
}
