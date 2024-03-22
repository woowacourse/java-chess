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

        if (isStart(chessBoard)) {
            outputView.printChessTable(chessBoard.getPieceSquares());
            play(chessBoard);
        }
    }

    private boolean isStart(final ChessBoard chessBoard) {
        final ChessCommand chessStartOrEndCommand = inputView.readStartCommand();
        return chessStartOrEndCommand.run(chessBoard);
    }

    private void play(final ChessBoard chessBoard) {
        try {
            if (isEnd(chessBoard)) {
                return;
            }
            outputView.printChessTable(chessBoard.getPieceSquares());
        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
        play(chessBoard);
    }

    private boolean isEnd(final ChessBoard chessBoard) {
        final ChessCommand chessMoveOrEndCommand = inputView.readMoveCommand();
        return !chessMoveOrEndCommand.run(chessBoard);
    }
}
