package chess2;

import chess2.controller.GameController;
import chess2.domain2.game2.Game;

public class Application {

    private static final GameController controller = new GameController();

    public static void main(String[] args) {
        Game game = controller.startGame();
        game = controller.playGame(game);
        controller.printGameOver(game);
    }
}
