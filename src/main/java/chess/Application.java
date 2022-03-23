package chess;

import chess.controller.GameController;
import chess.domain.ChessGame;

public class Application {

    private static final GameController controller = new GameController();

    public static void main(String[] args) {
        ChessGame game = controller.startGame();
        controller.playGame(game);
        controller.endGame(game);
    }
}
