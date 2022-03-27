package chess;

import chess.controller.GameController;
import chess.domain.game.Game;

public class Application {

    private static final GameController controller = new GameController();

    public static void main(String[] args) {
        Game game = controller.startGame();
        game = controller.playGame(game);
        controller.endGame(game);
    }
}
