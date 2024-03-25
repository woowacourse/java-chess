package chess;

import chess.controller.GameManager;
import chess.view.InputView;
import chess.view.MessageResolver;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        GameManager gameManager = new GameManager(new InputView(), new OutputView(new MessageResolver()));
        gameManager.run();
    }
}
