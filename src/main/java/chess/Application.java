package chess;

import chess.controller.ChessManager;
import chess.view.InputView;
import chess.view.MessageResolver;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        ChessManager chessManager = new ChessManager(new InputView(), new OutputView(new MessageResolver()));
        chessManager.start();
    }
}
