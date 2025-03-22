package chess;

import chess.view.InputView;
import chess.view.OutputView;

public class Game {
    public void start() {
        Board board = new Board();
        while (true) {
            OutputView.printBoard(board);
            Position start = InputView.readStartPosition();
            Position end = InputView.readEndPosition();
            board.movePiece(start, end);
        }
    }
}
