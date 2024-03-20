package chess;

import chess.domain.board.ChessBoardCreator;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessApplication {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        if (inputView.readGameStart().equals("start")) {
            ChessBoardCreator chessBoardCreator = new ChessBoardCreator();
            outputView.printChessBoardMessage(chessBoardCreator.create());
        }
    }
}
