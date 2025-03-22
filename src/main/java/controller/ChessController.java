package controller;

import chess.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Board board = new Board();
        while (true) {
            outputView.printBoard(board);
            String start = inputView.inputStart();
            String end = inputView.inputEnd();
            boolean isMoved = board.move(start, end);
            if (!isMoved) {
                outputView.printPieceNotMoved();
            }
        }
    }

}
