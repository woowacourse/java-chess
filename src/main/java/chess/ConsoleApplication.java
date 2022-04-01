package chess;

import chess.controller.ConsoleController;
import chess.domain.game.Game;

public class ConsoleApplication {

    private static final ConsoleController controller = new ConsoleController();

    public static void main(String[] args) {
        Game game = controller.startGame();
        game = controller.playGame(game);
        controller.printGameOver(game);
    }
}
