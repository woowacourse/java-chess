package chess.controller;

import chess.game.Game;
import java.util.Arrays;
import java.util.List;

public class GameController {

    public void move(final Game game, final String input) {
        if (game.isRunning()) {
            final List<String> splitInput = Arrays.asList(input.split(" "));
            game.run(splitInput.get(0), splitInput);
        }
    }
}
