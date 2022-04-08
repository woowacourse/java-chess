package chess;

import chess.controller.ConsoleController2;
import chess.domain.game.Game;
import chess.domain.game.NewGame;
import chess.view.InputView2;
import chess.view.OutputView;

public class ConsoleApplication2 {

    private static final ConsoleController2 controller = new ConsoleController2();
    private static final InputView2 inputView = new InputView2();

    public static void main(String[] args) {
        new OutputView().printGameInstructions();

        Game game = new NewGame();
        while (true) {
            game = controller.run(game, inputView.requestValidCommandInput());
        }
    }
}
