package chess;

import chess.controller.ConsoleController;
import chess.domain.game.Game;
import chess.domain.game.NewGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {

    private static final ConsoleController controller = new ConsoleController();
    private static final InputView inputView = new InputView();

    public static void main(String[] args) {
        new OutputView().printGameInstructions();

        Game game = new NewGame();
        while (true) {
            game = controller.run(game, inputView.requestValidCommandInput());
        }
    }
}
