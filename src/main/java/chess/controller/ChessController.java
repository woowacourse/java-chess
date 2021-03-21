package chess.controller;

import chess.domain.Game;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    public void run() {
        OutputView.printStartMessage();
        if (InputView.willNotPlayGame()) {
            return;
        }

        Game game = new Game();
        game.display();
        while (game.isNotEnd()) {
            List<String> positions = InputView.requestPositions();
            String from = positions.get(0);
            String to = positions.get(1);
            game.move(Position.from(from), Position.from(to));
            game.display();
        }

        if(InputView.willWatchScore()) {
            OutputView.printScore(game.score());
        }
    }
}
