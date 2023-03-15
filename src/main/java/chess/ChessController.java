package chess;

import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        Chessboard chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);
        inputView.printStartChess();
        while (!inputView.requestCommend().equals("end")) {
            outputView.printChessBoard(chessboard);
        }
    }
}
