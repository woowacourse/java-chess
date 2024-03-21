package chess;

import chess.controller.ChessGameController;
import chess.domain.Board;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView.printChessGameStartMessage();
        OutputView.printCommandGuideMessage();

        ChessGameController controller = new ChessGameController();

        controller.run();
    }
}
