package chess;

import domain.board.Board;
import domain.pieces.PiecesFactory;
import view.InputView;
import view.OutputView;

public class WebUIChessApplication {
    public static void main(String[] args) {
        OutputView.printStart();

        CommendType commendType = CommendType.answer(InputView.inputGameCommend());
        if (commendType == CommendType.START) {
            play();
        }
    }

    private static void play() {
        Board board = Board.createEmptyBoard();
        board.setAll(new PiecesFactory().getInstance());
        OutputView.printBoard(board);

        CommendType commendType = CommendType.answer(InputView.inputGameCommend());
        if (commendType == CommendType.START) {
            play();
        }
    }
}
