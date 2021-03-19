package chess;

import chess.domain.Game;
import chess.domain.Player;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Game game = new Game();

        OutputView.printStartMessage();
        game.display();
        Player white = new Player(Color.WHITE);
        Player black = new Player(Color.BLACK);
        while (game.isNotEnd()) {
            String[] inputString = InputView.requestInput();
            if ("move".equals(inputString[0])) {
                String from = inputString[1];
                String to = inputString[2];
                game.action(black, Position.from(from), Position.from(to));
            }
            game.display();

            inputString = InputView.requestInput();
            if ("move".equals(inputString[0])) {
                String from = inputString[1];
                String to = inputString[2];
                game.action(white, Position.from(from), Position.from(to));
            }
            game.display();
        }
    }
}
